import axios from 'axios';

export default {

    //Funzione per la registrazione

    signup(nomeNewUtente, cognomeNewUtente, codFiscNewUtente, passwordNewUtente, dataDiNascitaNewUtente, emailNewUtente, numeroTelefonoNewUtente) {
        return axios
            .post('http://localhost:8080/signup', {
                nomeNewUtente,
                cognomeNewUtente,
                codFiscNewUtente,
                passwordNewUtente,
                dataDiNascitaNewUtente,
                emailNewUtente,
                numeroTelefonoNewUtente
                }, {
                    crossDomain: true,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
            ).then(response => response.data);
    }
};