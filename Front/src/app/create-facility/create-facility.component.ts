import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormArray, Validators, ReactiveFormsModule } from '@angular/forms';
import { FacilityService } from '../facilities/facility.service';
import { Facility, Discipline, WorkDay } from '../facilities/facility.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-create-facility',
  templateUrl: './create-facility.component.html',
  styleUrls: ['./create-facility.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule]  // Ensure ReactiveFormsModule is imported here
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
      disciplines: this.fb.array([]),  // Initialize disciplines as a FormArray
      workDays: this.fb.array([])  // Initialize workDays as a FormArray
    });
  }

  // Getter for disciplines FormArray
  get disciplines(): FormArray {
    return this.facilityForm.get('disciplines') as FormArray;
  }

  // Getter for workDays FormArray
  get workDays(): FormArray {
    return this.facilityForm.get('workDays') as FormArray;
  }

  // Method to add a new discipline FormGroup to the disciplines FormArray
  addDiscipline(): void {
    this.disciplines.push(this.fb.group({
      name: ['', Validators.required]
    }));
  }

  // Method to remove a discipline FormGroup from the disciplines FormArray
  removeDiscipline(index: number): void {
    this.disciplines.removeAt(index);
  }

  // Method to add a new workDay FormGroup to the workDays FormArray
  addWorkDay(): void {
    this.workDays.push(this.fb.group({
      day: ['', Validators.required],
      fromTime: ['', Validators.required],
      untilTime: ['', Validators.required]
    }));
  }

  // Method to remove a workDay FormGroup from the workDays FormArray
  removeWorkDay(index: number): void {
    this.workDays.removeAt(index);
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







