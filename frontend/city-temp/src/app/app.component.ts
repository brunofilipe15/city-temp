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
import { SelectItem } from 'primeng/api';
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
  CELSIUS = 0,
  FAHRENHEIT = 1
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  @ViewChild("chart") chart: ChartComponent;
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

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getCities().subscribe(cities => {
      cities.forEach(city => {
        this.citiesOptions.push({label: city.name, value: city});
      });
      
      this.unitOfTemperatureSelect = unitOfTemperatureEnum.CELSIUS;
      this.city = this.citiesOptions[0].value;
      this.numberOfDays = this.numberOfDaysOptions[0].value;
      
      this.createCharts(this.numberOfDays, this.city.id, this.unitOfTemperatureSelect);
    });
  }

  onChangeNumberOfDays(event) {
    this.numberOfDays = event.value;
    this.createCharts(this.numberOfDays, this.city.id, this.unitOfTemperatureSelect);
  }
  
  onChangeCity(event) {
    this.city = event.value;
    this.createCharts(this.numberOfDays, this.city.id, this.unitOfTemperatureSelect);
  }

  onChangeUnitOfTemperature(event) {
    this.unitOfTemperatureSelect = event.value;
    this.createCharts(this.numberOfDays, this.city.id, this.unitOfTemperatureSelect);
  }

  async createCharts(numberOfDays: number, cityId: number, unitOfTemperature: unitOfTemperatureEnum) {
    this.chartReady = false;
    let temperatureCelsius = [];
    let temperatureFahrenheit = [];
    let dates = [];
    
    this.temperatures = await this.apiService.getTemperatures(numberOfDays, cityId);
    this.temperatures.forEach(temperature => {
      let date = temperature.localDate;
      dates.push(date);

      temperatureCelsius.push([date, temperature.tempCelsius]);
      temperatureFahrenheit.push([date, temperature.tempFahrenheit]);
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
  }

}


