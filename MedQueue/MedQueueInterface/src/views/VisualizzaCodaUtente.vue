<template>
  <ion-page>
    <ion-content id="main-content">
      <ion-header :translucent="true">
        <ion-toolbar>
          <ion-buttons slot="start">
            <ion-menu-button color="primary"></ion-menu-button>
          </ion-buttons>
          <ion-title>Visualizza Coda</ion-title>
        </ion-toolbar>
      </ion-header>
      <div id="container">
        <strong class="capitalize">Visualizza Coda</strong>
        <div class="selezione">
          <label>Seleziona la struttura:</label>
          <ion-select placeholder="Struttura" v-model="strutturaScelta">
            <ion-select-option id="str" v-bind:key="struttura" v-for="struttura in struture">{{ struttura }}
            </ion-select-option>
          </ion-select>
          <ion-button id="visualizza" @mouseover="getIdStruttura" @click="updatePrenotazioni" color="primary">Visualizza</ion-button>
        </div>

        <br>
        <br>
        <br>

        <div class="titolo1">Data</div>
        <div class="titolo2">Ora</div>
        <div class="titolo3">Tipo</div>
        <br>
        <br>

        <div class="colonna1">
          <div id="data" v-bind:key="data" v-for="data in date">
            <div>{{ data }}</div>
            <br>
          </div>
        </div>
        <div class="colonna2">
          <div id="ora" v-bind:key="ora" v-for="ora in ore">
            <div>{{ ora }}</div>
            <br>
          </div>
        </div>
        <div class="colonna3">
          <div id="prenotazione" v-bind:key="prenotazione" v-for="prenotazione in nomeOperazioni">
            <div>{{ prenotazione }}</div>
            <br>
          </div>
        </div>
      </div>
    </ion-content>
  </ion-page>
</template>

<script>
import prenotazioniAxios from '../axios/Prenotazioni'
import operazioneAxios from '../axios/Operazione'
import struttureAxios from '../axios/Strutture'
import {
  IonSelect,
  IonSelectOption,
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar
} from '@ionic/vue';
import router from "@/router";


export default {
  name: "visualizzaCoda",
  components: {
    IonSelect,
    IonSelectOption,
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar
  },
  data() {
    return {
      tmp: [],
      prenotazioni: [],
      ore: [],
      date: [],
      operazioni: [],
      nomeOperazioni: [],
      struture: [],
      strutturaScelta: "",
      selectedCod: ""
    };
  },
  created() {
    if (sessionStorage.getItem("codiceFiscale") === null) {
      router.push("/Home");
    }
    this.getStrutture();
  },
  methods: {

    getIdStruttura() {
      struttureAxios.getStrutturaByNome(this.strutturaScelta)
          .then((response) => {
            this.selectedCod = response.id;
          })
    },

    updatePrenotazioni() {

      for (let i = 0; i < this.nomeOperazioni.length; i++) {
        this.ore.pop();
        this.date.pop();
        this.operazioni.pop();
        this.nomeOperazioni.pop();
      }
      prenotazioniAxios.getPrenotazioniByStruttura(this.selectedCod)
          .then((response) => {
            if (response === '') {
              this.presentAlert();
              return null;
            } else {
              this.prenotazioni = response;

              for (let i = 0; i < this.prenotazioni.length; i++) {
                this.ore[i] = this.prenotazioni[i].ora;
                this.date[i] = this.prenotazioni[i].dataPrenotazione;
                this.operazioni[i] = this.prenotazioni[i].idOperazione;
              }
              this.operazioneString();
            }
          })
    },

    operazioneString() {
      for (let i = 0; i < this.operazioni.length; i++) {
        operazioneAxios.getOperazioneById(this.operazioni[i])
            .then((response) => {
              this.nomeOperazioni[i] = response.tipoOperazione;
            })
      }
    },

    getStrutture() {
      struttureAxios.getStrutture()
          .then((response) => {
            this.tmp = response;
            for (let i = 0; i < this.tmp.length; i++) {
              this.struture[i] = this.tmp[i].nome;
            }
          })
    }
  }
}
</script>


<style scoped>

.selezione {
  width: 20%;
}

ion-button {
  float: top;
}

div.colonna1 {
  float: left;
  margin-right: 33%;
  margin-left: 9%;
}

div.colonna2 {
  float: left;
  margin-right: 33%;
}

div.colonna3 {
  float: left;
}

div.titolo1 {
  font-weight: bold;
  float: left;
  margin-right: 36%;
  margin-left: 10%;
}

div.titolo2 {
  font-weight: bold;
  float: left;
  margin-right: 38%;
}

div.titolo3 {
  font-weight: bold;
  float: left;
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

ion-item.selected {
  --color: var(--ion-color-primary);
}
</style>