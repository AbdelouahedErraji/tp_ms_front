import axios from "axios";

const apiAchat = axios.create({
    baseURL:"http://localhost:8082/api/achat"
})
export default apiAchat;