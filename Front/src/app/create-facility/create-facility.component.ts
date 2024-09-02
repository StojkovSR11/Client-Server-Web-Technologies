import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FacilityService } from '../facilities/facility.service';
import { Facility } from '../facilities/facility.model';

@Component({
  selector: 'app-create-facility',
  templateUrl: './create-facility.component.html',
  styleUrls: ['./create-facility.component.css']
})
export class CreateFacilityComponent {
  facility: Facility = {
    id: 0,  // Set default or handle auto-increment
    name: '',
    description: '',
    createdAt: new Date().toISOString(),  // Set the current date
    address: '',
    city: '',
    totalRating: 0,
    active: true,
    workDays: [],
    disciplines: []
  };

  constructor(private facilityService: FacilityService, private router: Router) {}

  createFacility(): void {
    this.facilityService.createFacility(this.facility).subscribe(() => {
      this.router.navigate(['/']);  // Redirect to the facilities list or another page
    });
  }
}

