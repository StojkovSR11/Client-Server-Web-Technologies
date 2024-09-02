import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FacilityService } from '../facility.service';
import { Facility } from './facility.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-facilities',
  templateUrl: './facilities.component.html',
  styleUrls: ['./facilities.component.css'],
  standalone: true,
  imports: [CommonModule]  // Ensure CommonModule is imported
})
export class FacilitiesComponent implements OnInit {
  facilities$!: Observable<Facility[]>;

  constructor(private facilityService: FacilityService) {}

  ngOnInit(): void {
    this.facilities$ = this.facilityService.getFacilities();
  }
}











