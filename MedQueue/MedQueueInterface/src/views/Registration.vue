<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title>Registrazione</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Registrazione</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-content class="background">
        <div id="container">
          <img src="../../public/assets/logosvg_nobg.svg"/>
          <br>
          <strong class="capitalize">Registrazione</strong>
          <br>
          <form>
            <ion-label>Nome</ion-label>
            <ion-input id="nome" v-model="nome" placeholder="Nome" required="true"></ion-input>
            <ion-label>Cognome</ion-label>
            <ion-input id="cognome" v-model="cognome" placeholder="Cognome" required="true"></ion-input>
            <ion-label>Codice Fiscale</ion-label>
            <ion-input id="cf" v-model="codFisc" placeholder="CodiceFiscale"
                       pattern="[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$" required="true"></ion-input>
            <ion-label>Password</ion-label>
            <ion-input id="psw" v-model="password" type="password" placeholder="Password"
                       pattern="(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$"
                       required="true"
                       title="Deve contenere almeno 1 lettera maiuscola, 1 numero e 1 carattere speciale e deve essere lunga almeno 8 caratteri"></ion-input>
            <ion-label>Data di Nascita</ion-label>
            <ion-datetime id="data" v-model="dataDiNascita" displayFormat="DD-MM-YYYY" placeholder="Data di nascita" max="2003"
                          required="true"></ion-datetime>
            <ion-label>Indirizzo e-mail</ion-label>
            <ion-input id="email" v-model="email" placeholder="Indirizzo e-mail" pattern="\S+@\S+\.\S+"
                       required="true"></ion-input>
            <ion-label>Numero di telefono</ion-label>
            <ion-input id="numero" v-model="numeroTelefono" placeholder="Numero di telefono" patern="^[\+][0-9]{10,12}"
                       required="true"></ion-input>
            <ion-button id="registrazione" @click="autentication()" color="success"> Registrati</ion-button>
          </form>
        </div>
      </ion-content>
    </ion-content>
  </ion-page>
</template>
<script>
import singupAxios from "../axios/Autentication.js"
import router from "@/router";
import {
  IonDatetime,
  IonButton,
  IonLabel,
  IonInput,
  IonButtons,
  IonContent,
  IonHeader,
  IonMenuButton,
  IonPage,
  IonTitle,
  IonToolbar, alertController
} from '@ionic/vue';

export default {
  name: "Registration",
  data() {
    return {
      nome: "",
      cognome: "",
      codFisc: "",
      password: "",
      dataDiNascita: "",
      email: "",
      numeroTelefono: ""
    };
  },
  components: {
    IonDatetime,
    IonButton,
    IonLabel,
    IonInput,
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar
  },
  methods: {

    async autentication() {
      const data = this.dataDiNascita.split('T');
      console.log(data[0]);
      singupAxios.signup(this.nome, this.cognome, this.codFisc, this.password, data[0], this.email, this.numeroTelefono)
          .then((response) => {
            if (response === 0) {
              this.alertPsw();
            } else if (response === 1){
              router.push("/Accesso");
            } else if (response === 2){
              this.alertCf();
            } else if (response === 3){
              this.alert()
            } else if (response === 4){
              this.alertAge();
            }
          })
    },

    async alertPsw() {
      const alert = await alertController.create({
        header: 'Password non conforme allo standard',
        message:'Deve contenere almeno 1 lettera maiuscola, 1 numero e 1 carattere speciale e deve essere lunga almeno 8 caratteri',
        buttons: ['OK'],
      });
      return alert.present();

    },

    async alertCf() {
      const alert = await alertController.create({
        header: 'Codice Fiscale già esistente',
        buttons: ['OK'],
      });
      return alert.present();

    },
    async alert() {
      const alert = await alertController.create({
        header: 'Alcuni campi sono errati',
        buttons: ['OK'],
      });
      return alert.present();

    },

    async alertAge() {
      const alert = await alertController.create({
        header: 'Bisogna essere maggiorenni per registrarsi',
        buttons: ['OK'],
      });
      return alert.present();

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

ion-label {
  color: black;
  font-weight: bold;
}

ion-input {
  color: black;
  margin-left: 40%;
  position: center;
  min-width: 193px;
  width: 20%;
}

ion-datetime {
  color: black;
}
</style>