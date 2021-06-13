<template>
  <ion-page>
    <ion-content content="menu">
      <ion-header :translucent="true">
        <ion-toolbar>
          <ion-buttons slot="start">
            <ion-menu-button color="primary"></ion-menu-button>
          </ion-buttons>
          <ion-title>Prenotazione</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-content class="background">
        <div id="container">
          <img src="../../public/assets/logosvg_nobg.svg"/>
          <br>
          <strong class="capitalize">Prenota</strong>
          <br>
          <br>
          <br>
          <ion-label>Seleziona Struttura</ion-label>
          <ion-select @mouseover="getStrutture" v-model="struttura" placeholder="Struttura">
            <ion-select-option id="struttura" v-bind:key="struttura" v-for="struttura in listaStrutture">
              {{ struttura }}
            </ion-select-option>
          </ion-select>
          <br>

          <ion-label>Seleziona Operazione</ion-label>
          <ion-select @mouseover="getOperazioni" v-model="operazione" placeholder="Operazione">
            <ion-select-option id="operazione" v-bind:key="operazione" v-for="operazione in listaOperazioni">
              {{ operazione }}
            </ion-select-option>
          </ion-select>
          <br>

          <ion-label>Seleziona Data</ion-label>
          <ion-datetime v-model="data" max="2023" displayFormat="DD MM YY" placeholder="Data"></ion-datetime>
          <br>
          <ion-label>Seleziona Orario</ion-label>
          <ion-select @mouseover="getOrari" v-model="ora" placeholder="Orario">
            <ion-select-option id="ora" v-bind:key="ora" v-for="ora in listaOrari">{{ ora }}</ion-select-option>
          </ion-select>
          <br>
          <ion-button @click="addPrenotazione" color="success"> Prenota</ion-button>
        </div>
      </ion-content>
    </ion-content>
  </ion-page>
</template>

<script>
import prenotazioniAxios from '../axios/Prenotazioni'
import struttureAxios from '../axios/Strutture'
import operazioneAxios from '../axios/Operazione'
import {
  IonButton,
  IonSelectOption,
  IonSelect,
  IonDatetime,
  IonContent,
  IonButtons,
  IonHeader,
  IonMenuButton,
  IonPage,
  IonTitle,
  IonToolbar,
  alertController
} from '@ionic/vue';
import router from "@/router";


export default {
  name: "Prenotazione",
  components: {
    IonButton,
    IonSelectOption,
    IonSelect,
    IonDatetime,
    IonContent,
    IonButtons,
    IonHeader,
    IonMenuButton,
    IonPage,
    IonTitle,
    IonToolbar
  },
  data() {
    return {
      id: 1,
      tmp: [],
      listaStrutture: [],
      listaOperazioni: [],
      listaOrari: [],
      prova1: "",
      prova2: "",
      struttura: "",
      idStruttura: "",
      operazione: "",
      idOperazione: "",
      data: "",
      ora: "",
      dataOggi: {
        year: new Date().getFullYear(),
        month: new Date().getMonth(),
        day: new Date().getDay()
      }
    }
  },
  created() {
    if (sessionStorage.getItem("codiceFiscale") === null) {
      router.push("/Home");
    }
  },
  updated() {
    this.struttura = "";
    this.data = "";
    this.operazione = "";
    this.ora = "";
  },
  methods: {

    getIdStruttura() {
      return new Promise((resolve, reject) => {
        struttureAxios.getStrutturaByNome(this.struttura)
            .then((response) => {
              this.prova1 = response;
              this.idStruttura = this.prova1.id;
              return resolve(true);
            }).catch(err => {
          reject(console.log("Errore!"))
        })
      })
    },

    getIdOperazione() {
      return new Promise((resolve, reject) => {
        operazioneAxios.getOperazioneByNome(this.operazione)
            .then((response) => {
              this.prova2 = response;
              this.idOperazione = this.prova2.id;
              return resolve(true);
            }).catch(err => {
          reject(console.log("Errore!"))
        })
      })
    },

    async addPrenotazione() {
      const data = this.data.split('T');
      try {
        await this.getIdStruttura();
        await this.getIdOperazione();
      } catch (err) {
        return null;
      }

      prenotazioniAxios.addPrenotazione(sessionStorage.getItem("codiceFiscale"), this.ora, this.idOperazione, this.idStruttura, data[0])
          .then((response) => {
            if (response === "") {
              console.log("Errore");
              return null;
            } else if (response === false) {
              this.presentAlert();
            } else {
              router.push("/HomeUtente");
            }
          })
    },
    async presentAlert() {
      const alert = await alertController.create({
        header: "Errore nella data o nell'ora",
        buttons: ['OK'],
      });
      return alert.present();
    },

    getStrutture() {
      struttureAxios.getStrutture()
          .then((response) => {
            this.tmp = response;

            for (let i = 0; i < this.tmp.length; i++) {
              this.listaStrutture[i] = this.tmp[i].nome;
            }
          })
    },

    getOperazioni() {
      operazioneAxios.getOperazioni()
          .then((response) => {
            this.tmp = response;

            for (let i = 0; i < this.tmp.length; i++) {
              this.listaOperazioni[i] = this.tmp[i].tipoOperazione;
            }
          })
    },

    async getOrari() {
      const data = this.data.split('T');
      try {
        await this.getIdStruttura();
        await this.getIdOperazione();
      } catch (err) {
        return null;
      }

      prenotazioniAxios.getOrariDisponibili(this.idStruttura, this.idOperazione, data[0])
          .then((response) => {
            this.tmp = response;
            for (let i = 0; i < this.tmp.length; i++) {
              this.listaOrari[i] = this.tmp[i];
            }
          })
    }

  }
}

</script>

<style scoped>

img {
  height: 200px;
  width: 200px;
  position: center;
}

ion-content.background {
  --background: url(../../public/assets/CartellinaAllungata.svg) 0 0/100% 100% no-repeat;

}

#container {
  text-align: center;
  position: relative;
  left: 0;
  right: 0;
  top: 50%;
  transform: translateY(-50%);
}

#container strong {
  color: black;
  font-size: 20px;
  line-height: 26px;
}

#container p {
  font-size: 16px;
  line-height: 22px;
  color: #8c8c8c;
  margin: 0;
}

#container a {
  text-decoration: none;
}

ion-menu ion-content {
  --background: var(--ion-item-background, var(--ion-background-color, #fff));
}

ion-menu.md ion-content {
  --padding-start: 8px;
  --padding-end: 8px;
  --padding-top: 20px;
  --padding-bottom: 20px;
}

ion-menu.md ion-list {
  padding: 20px 0;
}

ion-menu.md ion-note {
  margin-bottom: 30px;
}

ion-menu.md ion-list-header,
ion-menu.md ion-note {
  padding-left: 10px;
}

ion-menu.md ion-list#menu-list {
  border-bottom: 1px solid var(--ion-color-step-150, #d7d8da);
}

ion-menu.md ion-list#menu-list ion-list-header {
  font-size: 22px;
  font-weight: 600;

  min-height: 20px;
}

ion-menu.md ion-list#labels-list ion-list-header {
  font-size: 16px;

  margin-bottom: 18px;

  color: #757575;

  min-height: 26px;
}

ion-menu.md ion-item {
  --padding-start: 10px;
  --padding-end: 10px;
  border-radius: 4px;
}

ion-menu.md ion-item.selected {
  --background: rgba(var(--ion-color-primary-rgb), 0.14);
}

ion-menu.md ion-item.selected ion-icon {
  color: var(--ion-color-primary);
}

ion-menu.md ion-item ion-icon {
  color: #616e7e;
}

ion-menu.md ion-item ion-label {
  font-weight: 500;
}

ion-menu.ios ion-content {
  --padding-bottom: 20px;
}

ion-menu.ios ion-list {
  padding: 20px 0 0 0;
}

ion-menu.ios ion-note {
  line-height: 24px;
  margin-bottom: 20px;
}

ion-menu.ios ion-item {
  --padding-start: 16px;
  --padding-end: 16px;
  --min-height: 50px;
}

ion-menu.ios ion-item.selected ion-icon {
  color: var(--ion-color-primary);
}

ion-menu.ios ion-item ion-icon {
  font-size: 24px;
  color: #73849a;
}

ion-menu.ios ion-list#labels-list ion-list-header {
  margin-bottom: 8px;
}

ion-menu.ios ion-list-header,
ion-menu.ios ion-note {
  padding-left: 16px;
  padding-right: 16px;
}

ion-menu.ios ion-note {
  margin-bottom: 8px;
}

ion-note {
  display: inline-block;
  font-size: 16px;

  color: var(--ion-color-medium-shade);
}

ion-item.selected {
  --color: var(--ion-color-primary);
}

ion-label {
  color: black;
}

ion-input {
  color: black;
  margin-left: 40%;
  position: center;
  min-width: 193px;
  width: 20%;
}

ion-select {
  color: black;
  margin-left: 40%;
  position: center;
  min-width: 193px;
  width: 20%;
}

ion-datetime {
  color: black;
  margin-left: 40%;
  position: center;
  min-width: 193px;
  width: 20%;
}

</style>