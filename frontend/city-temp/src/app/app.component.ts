import { Component, OnInit, ViewChild } from '@angular/core';
import { ApiService } from './api.service';
import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexDataLabels,
  ApexYAxis,
  ApexFill,
  ApexMarkers,
  ApexStroke,
  ApexLegend
} from "ng-apexcharts";
import { Temperature } from './temperature';
import { MessageService, SelectItem } from 'primeng/api';
import { City } from './city';

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  dataLabels: ApexDataLabels;
  yaxis: ApexYAxis;
  fill: ApexFill;
  stroke: ApexStroke;
  markers: ApexMarkers;
  colors: string[];
  legend: ApexLegend;
};

export enum unitOfTemperatureEnum {
  CELSIUS,
  FAHRENHEIT
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [MessageService]
})
export class AppComponent implements OnInit {

  public chartBig: Partial<ChartOptions>;
  public chartSmall: Partial<ChartOptions>;

  temperatures: Temperature[];
  chartReady = false;
  numberOfDaysOptions = [
    {label: '1', value: 1},
    {label: '2', value: 2},
    {label: '3', value: 3},
    {label: '4', value: 4},
    {label: '5', value: 5}
  ];
  numberOfDaysSelect: SelectItem;
  numberOfDays: number;

  citiesOptions: SelectItem[] = [];
  city: City;

  unitOfTemperature = [
    {name: 'Celsius', value: unitOfTemperatureEnum.CELSIUS},
    {name: 'Fahrenheit', value: unitOfTemperatureEnum.FAHRENHEIT}
  ];
  unitOfTemperatureSelect: any;

  temperatureNow: Temperature;
  UnitOfTemperatureEnum = unitOfTemperatureEnum;

  constructor(private apiService: ApiService,
    private messageService: MessageService) { }

  ngOnInit() { 
    this.apiService.getCities().subscribe(cities => {
      cities.forEach(city => {
        this.citiesOptions.push({label: city.name, value: city});
      });
      
      this.unitOfTemperatureSelect = unitOfTemperatureEnum.CELSIUS;
      this.city = this.citiesOptions[0].value;
      this.numberOfDays = this.numberOfDaysOptions[0].value;
      
      this.getTemperatureNow(this.city);
      this.createCharts(this.numberOfDays, this.city.id, this.unitOfTemperatureSelect);
    }, error => { this.messageService.add({ severity: 'error', summary: 'Error', detail: error ? 'Service Unavailable' : error.error.message })});

  }

  onChangeNumberOfDays(event) {
    this.numberOfDays = event.value;
    this.createCharts(this.numberOfDays, this.city.id, this.unitOfTemperatureSelect);
  }
  
  onChangeCity(event) {
    this.city = event.value;
    this.getTemperatureNow(this.city);
    this.createCharts(this.numberOfDays, this.city.id, this.unitOfTemperatureSelect);
  }

  onChangeUnitOfTemperature(event) {
    this.unitOfTemperatureSelect = event.value;
    this.createCharts(this.numberOfDays, this.city.id, this.unitOfTemperatureSelect);
  }

  createCharts(numberOfDays: number, cityId: number, unitOfTemperature: unitOfTemperatureEnum) {
    this.chartReady = false;
    let temperatureCelsius = [];
    let temperatureFahrenheit = [];
    let dates = [];
    
    this.apiService.getTemperatures(numberOfDays, cityId).subscribe(temperatures => {
      this.temperatures = temperatures;
      this.temperatures.forEach(temperature => {
        let date = temperature.localDate;
        dates.push(date);
        temperatureCelsius.push([date, Math.round(temperature.tempCelsius * 10) / 10]);
        temperatureFahrenheit.push([date,  Math.round(temperature.tempFahrenheit * 10) / 10]);
      });
  
      this.chartBig = {
        series: [
          {
            name: "Temperature",
            data: unitOfTemperature === unitOfTemperatureEnum.CELSIUS ? temperatureCelsius : temperatureFahrenheit,
            color: "#556CBA"
          },
        ],
        chart: {
          id: "chartBig",
          type: "line",
          height: 230,
          toolbar: {
            autoSelected: "pan",
            show: false
          }
        },
        colors: ["#546E7A"],
        stroke: {
          curve: 'smooth',
          width: 3
        },
        fill: {
          opacity: 1
        },
        markers: {
          size: 0
        },
        xaxis: {
          type: "datetime"
        }
      };
  
      this.chartSmall = {
        series: [
          {
            name: "Temperature",
            data: unitOfTemperature === unitOfTemperatureEnum.CELSIUS ? temperatureCelsius : temperatureFahrenheit,
            color: "#556CBA"
          },
        ],
        chart: {
          id: "chartSmall",
          height: 130,
          type: "area",
          brush: {
            target: "chartBig",
            enabled: true
          },
          selection: {
            enabled: true,
            xaxis: {
              min: new Date(dates[0]).getTime(),
              max: new Date(dates[dates.length - 1]).getTime()
            }
          }
        },
        colors: ["#008FFB"],
        fill: {
          type: 'gradient',
          gradient: {
            opacityFrom: 1,
            opacityTo: 0.1,
          },
        },
        xaxis: {
          type: "datetime",
          tooltip: {
            enabled: false
          }
        },
        yaxis: {
          tickAmount: 2
        },
        legend: {
          show: false
        }
      };
      this.chartReady = true;
    },
    error => { this.messageService.add({ severity: 'error', summary: 'Error', detail: error ? 'Service Unavailable' : error.error.message })});      
  }

  getTemperatureNow(city: City) {
    this.apiService.getNowTemperature(city.id).subscribe(temperatureNow => {
        this.temperatureNow = temperatureNow;
        this.temperatureNow.tempFahrenheit = Math.round(temperatureNow.tempFahrenheit * 10) / 10;
        this.temperatureNow.tempCelsius = Math.round(temperatureNow.tempCelsius * 10) / 10;
      },
    error => { this.messageService.add({ severity: 'error', summary: 'Error', detail: error ? 'Service Unavailable' : error.error.message })});    
  }

}


