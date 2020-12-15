import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Temperature } from './temperature';
import { City } from './city';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  apiURL: string = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  async getTemperatures(numberOfDays: number, cityId: number){
    return await this.httpClient.get<Temperature[]>(this.apiURL + '/city/' + cityId +'/temperatures?numbersOfDays=' + numberOfDays).toPromise();
  }

  getCities() {
    return this.httpClient.get<City[]>(this.apiURL+ '/city')
  }
}