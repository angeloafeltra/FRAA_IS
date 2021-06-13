import axios from 'axios';

export default {

    //Funzione per prelevare la prenotazione attraverso l'id

    getOperazioneById(idOperazioneGet) {
        return axios.post('http://localhost:8080/operazione/{id}', {
            idOperazioneGet
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    //Funzione per prelevare tutte le poerazioni

    getOperazioni() {
        return axios.post('http://localhost:8080/operazioni', {}, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    //Funzione per prelevare un'operazione attraverso il nome

    getOperazioneByNome(tipoOperazioneGet) {
        return axios.post('http://localhost:8080/operazione/{tipo}', {
            tipoOperazioneGet
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    }
};