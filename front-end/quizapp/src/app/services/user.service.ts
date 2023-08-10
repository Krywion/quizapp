import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/user';
   }

  public login(user: User) {
    console.log(user);
    return this.http.post(this.url + '/login', user);
  }
}
