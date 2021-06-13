<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title>Convalida Prenotazione</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Convalida Prenotazione</ion-title>
        </ion-toolbar>
      </ion-header>
      <div id="container">

        <label>Inserire il Codice Fiscale</label>
        <br>
        <br>
        <ion-input id="cf" v-model="codiceFiscale" placeholder="CodiceFiscale"></ion-input>
        <br>
        <ion-button @click="convalda" color="primary">Convalida</ion-button>


      </div>

    </ion-content>
  </ion-page>
</template>

<script>
import prenotazioniAxios from '../axios/Prenotazioni'
import {
  IonInput,
  IonButton,
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  alertController
} from '@ionic/vue';

export default {
  name: "Convalida",
  components: {
    IonInput,
    IonButton,
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar,
  },
  data() {
    return {
      codiceFiscale: ""
    };
  },
  methods: {
    async convalda() {
      await prenotazioniAxios.convalida(this.codiceFiscale)
          .then((response) => {
            if (response === 1) {
              this.alertGood();
            } else if (response === 2) {
              this.alertDay();
            } else if (response === 0) {
              this.alertCd()
            } else if (response === 3) {
              this.alertPre()
            } else if (response === 4) {
              this.alertPost()
            } else if (response === 5) {
              this.alertConv()
            }
          })
    },

    async alertGood() {
      const alert = await alertController.create({
        header: 'Prenotazione Convalidata',
        buttons: ['OK'],
      });
      return alert.present();
    },

    async alertDay() {
      const alert = await alertController.create({
        header: 'Non ci sono prenotazioni per oggi',
        buttons: ['OK'],
      });
      return alert.present();
    },

    async alertCd() {
      const alert = await alertController.create({
        header: 'Codice Fiscale non trovato',
        buttons: ['OK'],
      });
      return alert.present();
    },
    async alertPre() {
      const alert = await alertController.create({
        header: 'Sei arrivato troppo presto, torna più tardi',
        buttons: ['OK'],
      });
      return alert.present();
    },
    async alertPost() {
      const alert = await alertController.create({
        header: 'Sei arrivato troppo tardi, la tua prenotazione è scaduta',
        buttons: ['OK'],
      });
      return alert.present();
    },
    async alertConv() {
      const alert = await alertController.create({
        header: 'Prenotazione già convalidata',
        buttons: ['OK'],
      });
      return alert.present();

    }

  }
}
</script>

<style scoped>

label {
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
</style>