import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Temperature } from './temperature';
import { City } from './city';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  apiURL: string = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  getTemperatures(numberOfDays: number, cityId: number): Observable<Temperature[]>{
    return this.httpClient.get<Temperature[]>(this.apiURL + '/city/' + cityId +'/temperatures?numbersOfDays=' + numberOfDays);
  }

  getCities(): Observable<City[]>{
    return this.httpClient.get<City[]>(this.apiURL+ '/city');
  }

  getNowTemperature(cityId: number): Observable<Temperature>{
    return this.httpClient.get<Temperature>(this.apiURL + '/city/' + cityId +'/temperature-now');
  }
  
}