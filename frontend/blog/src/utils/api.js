import axios from 'axios'
import {API_BASE_URL, ACCESS_TOKEN_NAME} from '../constants/apiConstants'
import uuid from 'react-uuid'
import { useParams } from 'react-router';

export function getPosts() {
  return axios.get(API_BASE_URL+'/page', { headers: { 'Authorization': localStorage.getItem(ACCESS_TOKEN_NAME) }})
}

export function getPost(id) {
  return axios.get(API_BASE_URL+'/page/'+id, { headers: { 'Authorization': localStorage.getItem(ACCESS_TOKEN_NAME) }})
}

export function postPost({ title, body }) {
  return axios({
    method: 'put',
    url: API_BASE_URL+'/page/'+uuid(),
    data: {
      title,
      body,
    },
    headers: { 'Authorization': localStorage.getItem(ACCESS_TOKEN_NAME) }
  })
}

export default function PageId() {
    const { idPage } = useParams();
}