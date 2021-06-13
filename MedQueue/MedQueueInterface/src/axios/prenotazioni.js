import axios from 'axios';


export default {

    //Funizone per prelevare tutte le prenotazioni su una struttura

    getPrenotazioniByStruttura(getAllPrenotazionyByStruttura) {
        return axios.post('http://localhost:8080/visualizzaCoda/{id}', {
            getAllPrenotazionyByStruttura
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    //Funzione per aggiungere una prenotazione

    addPrenotazione(newPrenotazioniCf, newPrenotazioniOra, newPrenotazioniIdOp, newPrenotazioniIdS, newPrenotazioneData) {
        return axios.post('http://localhost:8080/newPrenotazione', {
            newPrenotazioniCf,
            newPrenotazioniOra,
            newPrenotazioniIdOp,
            newPrenotazioniIdS,
            newPrenotazioneData
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    //Funzione per prelevare gli orari disponiblili per la prenotazione
    //in base al giorno, alla struttura e al tipo di operazione

    getOrariDisponibili(idStruttura, idOperazione, PrenotazioneData) {
        return axios.post('http://localhost:8080/getOrari', {
            idStruttura,
            idOperazione,
            PrenotazioneData
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    //Funzione per prelevare tutte le prenotazioni di un utente

    getPrenotazioneByCf(getPrenotazioniByCf) {
        return axios.post('http://localhost:8080/prenotazioniUtente/{cf}', {
            getPrenotazioniByCf
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    //Funzione per eliminare una prenotazione

    elimina(deletePrenotazioniId) {
        return axios.post('http://localhost:8080/deletePrenotazione', {
            deletePrenotazioniId
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    //Funzione per effettuare la convalida della prenotazione

    convalida(convalidaPrenotazione) {
        return axios.post('http://localhost:8080/convalida', {
            convalidaPrenotazione
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    }
};