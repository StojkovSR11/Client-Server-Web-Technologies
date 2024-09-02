import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FacilityService } from '../facilities/facility.service';
import { Facility } from '../facilities/facility.model';

@Component({
  selector: 'app-create-facility',
  templateUrl: './create-facility.component.html',
  styleUrls: ['./create-facility.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule]  // Import ReactiveFormsModule here
})
export class CreateFacilityComponent {
  facilityForm: FormGroup;

  constructor(
    private facilityService: FacilityService, 
    private router: Router, 
    private fb: FormBuilder
  ) {
    this.facilityForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      createdAt: [new Date().toISOString()],
      address: ['', Validators.required],
      city: ['', Validators.required],
      workDays: [[]],
      disciplines: [[]]
    });
  }

  createFacility(): void {
    if (this.facilityForm.valid) {
      const facility: Facility = {
        ...this.facilityForm.value,
        totalRating: 0,  // Set default value
        active: true     // Set default value
      };
      this.facilityService.createFacility(facility).subscribe(() => {
        this.router.navigate(['/']);  // Redirect to the facilities list or another page
      });
    }
  }
}




