import axios from 'axios';

export default {

    //Funzione per il login

    login(username, password) {
        return axios
            .post('http://localhost:8080/login', {
                    username,
                    password
                }, {
                    crossDomain: true,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
            ).then(response => response.data);
    }
};