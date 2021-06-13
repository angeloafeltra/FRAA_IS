<template>
  <ion-page @mouseover="getPrenotazioni">
    <ion-content content="menu">
      <ion-header :translucent="true">
        <ion-toolbar>
          <ion-buttons slot="start">
            <ion-menu-button color="primary"></ion-menu-button>
          </ion-buttons>
          <ion-title>Visualizza Prenotazioni</ion-title>
        </ion-toolbar>
      </ion-header>
      <div id="container">
        <strong class="capitalize">Prenotazioni</strong>
        <br>
        <br>
        <br>
        <div class="titolo1">Data</div>
        <div class="titolo2">Struttura</div>
        <div class="titolo3">Tipo</div>
        <div class="titolo4">Ora</div>
        <div class="titolo5">Elimina</div>
        <div class="colonna1">
          <div id="data" v-bind:key="data" v-for="data in date">
            <br>
            <div>{{ data }}</div>
            <br>
          </div>
        </div>
        <div class="colonna2">
          <div id="struttura" v-bind:key="struttura" v-for="struttura in strutture">
            <br>
            <div>{{ struttura }}</div>
            <br>
          </div>
        </div>
        <div class="colonna3">
          <div id="tipo" v-bind:key="tipo" v-for="tipo in tipi">
            <br>
            <div>{{ tipo }}</div>
            <br>
          </div>
        </div>
        <div class="colonna4">
          <div id="ora" v-bind:key="ora" v-for="ora in ore">
            <br>
            <div>{{ ora }}</div>
            <br>
          </div>
        </div>
        <div class="colonna5">

          <div id="id" v-bind:key="id" v-for="id in idPrenotazioni">
            <ion-button id="elimina" @click="eliminaPrenotazione(id)" color="danger">Elimina</ion-button>
            <br>
            <br>
          </div>
        </div>
      </div>
    </ion-content>
  </ion-page>
</template>

<script>
import prenotazioniAxios from '../axios/Prenotazioni'
import struttureAxios from '../axios/Strutture'
import operazioneAxios from '../axios/Operazione'
import {
  IonButton,
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
      tmp: "",
      idPrenotazioni: [],
      prenotazioni: [],
      date: [],
      idStrutture: [],
      strutture: [],
      idTipi: [],
      tipi: [],
      ore: [],
    }
  },
  created() {
    if (sessionStorage.getItem("codiceFiscale") === null) {
      router.push("/Home");
    }
    this.getPrenotazioni();
  },
  methods: {

    async getPrenotazioni() {
      prenotazioniAxios.getPrenotazioneByCf(sessionStorage.getItem("codiceFiscale"))
          .then((response) => {
            this.prenotazioni = response;

            for (let i = 0; i < this.prenotazioni.length; i++) {
              this.idPrenotazioni[i] = this.prenotazioni[i].id;
              this.date[i] = this.prenotazioni[i].dataPrenotazione;
              this.idStrutture[i] = this.prenotazioni[i].idStruttura;
              this.idTipi[i] = this.prenotazioni[i].idOperazione;
              this.ore[i] = this.prenotazioni[i].ora;
            }
          })

      this.getNomeStruttura();
      this.getNomeOperazione();

    },

    getNomeStruttura() {
      for (let i = 0; i < this.idStrutture.length; i++) {
        struttureAxios.getStrutturaById(this.idStrutture[i])
            .then((response) => {
              this.tmp = response;

              this.strutture[i] = this.tmp.nome;
            })
      }
    },

    getNomeOperazione() {
      for (let i = 0; i < this.idTipi.length; i++) {
        operazioneAxios.getOperazioneById(this.idTipi[i])
            .then((response) => {
              this.tmp = response;
              this.tipi[i] = this.tmp.tipoOperazione;
            })
      }
    },

    async eliminaPrenotazione(id) {
      const toast = await alertController
          .create({
            header: 'Sei sicuro di voler eliminare questa prenotazione ?',
            position: 'middle',
            buttons: [
              {
                side: 'start',
                text: 'Annulla',
                handler: () => {
                  console.log('Cancellazione annullata');
                }
              },
              {
                text: 'Conferma',
                handler: () => {
                  prenotazioniAxios.elimina(id)
                      .then((response) => {
                        console.log("Successo!");
                        window.location.reload();
                      })
                }
              }
            ]
          })
      return toast.present();

    }

  }

}
</script>

<style scoped>

ion-button {
  height: 30px;
}

div.colonna1 {
  float: left;
  margin-right: 18%;
  margin-left: 9%;
}

div.colonna2 {
  float: left;
  margin-right: 16%;
}

div.colonna3 {
  float: left;
  margin-right: 17%;
}

div.colonna4 {
  float: left;
  margin-right: 2%;
}

div.titolo1 {
  font-weight: bold;
  float: left;
  margin-right: 20%;
  margin-left: 10%;
}

div.titolo2 {
  font-weight: bold;
  float: left;
  margin-right: 20%;
}

div.titolo3 {
  font-weight: bold;
  float: left;
  margin-right: 20%;
}

div.titolo4 {
  font-weight: bold;
  float: left;
  margin-right: 3%;
}

div.titolo5 {
  font-weight: bold;
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

#top {
  font-weight: bolder;
}

ion-item.selected {
  --color: var(--ion-color-primary);
}
</style>