import axios from 'axios';

export default {

    //Funzione per prelevare tutte le strutture disponibili

    getStrutture() {
        return axios.post('http://localhost:8080/strutture', {}, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    //Funzione per prelevare una struttura atraverso il suo nome

    getStrutturaByNome(nomeStrutturaGet) {
        return axios.post('http://localhost:8080/struttura/{nome}', {
            nomeStrutturaGet
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    //Funzione per prelevare una struttura atraverso il suo id

    getStrutturaById(idStrutturaGet) {
        return axios.post('http://localhost:8080/struttura/{id}', {
            idStrutturaGet
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    }

}