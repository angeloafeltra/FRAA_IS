<template>
  <ion-page >
    <ion-content id="menu">
      <ion-header :translucent="true">
        <ion-toolbar>
          <ion-buttons slot="start">
            <ion-menu-button id="bottone" color="primary"></ion-menu-button>
          </ion-buttons>
          <ion-title>Home</ion-title>
        </ion-toolbar>
      </ion-header>
      <div id="container">
        <img src="../../public/assets/logosvg_nobg.svg"/>
        <br>
        <strong class="capitalize">Benvenuto {{ nome }}</strong>
        <p>Da qui puoi raggiungere tutte le funzionalità offerte agli utenti,</p>
        <p>sia utilizzando questa schermata sia utlizzando il menù al lato</p>
        <ion-button id="prenota" @click="goPrenotazione"></ion-button>
        <ion-button id="guarda" @click="goLista"></ion-button>
        <ion-button id="coda" @click="goCalendar"></ion-button>
      </div>
    </ion-content>
  </ion-page>
</template>

<script>
import {
  IonContent,
  IonButtons,
  IonHeader,
  IonMenuButton,
  IonPage,
  IonTitle,
  IonToolbar,
} from '@ionic/vue';
import router from "@/router";


export default {
  name: "HomeUtente",
  components: {
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
      nome: sessionStorage.getItem("nome")
    }
  },
  created() {
    if (sessionStorage.getItem("codiceFiscale") === null) {
      router.push("/Home");
    }
  },
  mounted() {
    this.verificaSessione();
  },
  methods: {
    goPrenotazione() {
      router.push("/Prenotazione")
    },
    goLista() {
      router.push("VisualizzaPrenotazioni")
    },
    goCalendar() {
      router.push("/VisualizzazioneCodaUtente")
    },
    verificaSessione() {
      console.log(sessionStorage.getItem("codiceFiscale"))
      if (sessionStorage.getItem("codiceFiscale") === null) {
        router.push("/Home");
      }
    }

  },

}
</script>

<style scoped>

img {
  height: 200px;
  width: 200px;
  position: center;
}

ion-button {
  height: 150px;
  width: 150px;
  padding: 10px;
}

ion-button#prenota {
  --background: url(../../public/assets/Prenotazioni.svg) 0 0/100% 100% no-repeat;
}

ion-button#guarda {
  --background: url(../../public/assets/VisualizzaPrenotazioni.svg) 0 0/100% 100% no-repeat;

}

ion-button#coda {
  --background: url(../../public/assets/VisualizzaCoda.svg) 0 0/100% 100% no-repeat;

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