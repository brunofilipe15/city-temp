import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Temperature } from './temperature';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  apiURL: string = 'http://www.server.com/api/';

  constructor(private httpClient: HttpClient) {}

  public getContacts(){
    return this.httpClient.get<Temperature[]>('http://localhost:8080/city/1/temperatures?numbersOfDays=1');
}
}