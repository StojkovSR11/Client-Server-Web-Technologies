import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { FacilityService } from '../facilities/facility.service';
import { Facility, Discipline, WorkDay } from '../facilities/facility.model';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-facility',
  templateUrl: './edit-facility.component.html',
  styleUrls: ['./edit-facility.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule]
})
export class EditFacilityComponent implements OnInit {
  facilityForm: FormGroup;
  facilityId!: number;

  constructor(
    private route: ActivatedRoute,
    private facilityService: FacilityService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.facilityForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      address: ['', Validators.required],
      city: ['', Validators.required],
      disciplines: this.fb.array([]),
      workDays: this.fb.array([]),
    });
  }

  ngOnInit(): void {
    this.facilityId = +this.route.snapshot.paramMap.get('id')!;
    this.facilityService.getFacilityById(this.facilityId).subscribe((facility) => {
      this.facilityForm.patchValue(facility);
      this.setDisciplines(facility.disciplines);
      this.setWorkDays(facility.workDays);
    });
  }

  get disciplines(): FormArray {
    return this.facilityForm.get('disciplines') as FormArray;
  }

  get workDays(): FormArray {
    return this.facilityForm.get('workDays') as FormArray;
  }

  setDisciplines(disciplines: Discipline[]): void {
    const disciplineFGs = disciplines.map(discipline => this.fb.group({
      id: [discipline.id],  // Ensure id is included
      name: [discipline.name, Validators.required]
    }));
    const disciplineFormArray = this.fb.array(disciplineFGs);
    this.facilityForm.setControl('disciplines', disciplineFormArray);
  }
  
  setWorkDays(workDays: WorkDay[]): void {
    const workDayFGs = workDays.map(workDay => this.fb.group({
      id: [workDay.id],  // Ensure id is included
      day: [workDay.day, Validators.required],
      fromTime: [workDay.fromTime, Validators.required],
      untilTime: [workDay.untilTime, Validators.required]
    }));
    const workDayFormArray = this.fb.array(workDayFGs);
    this.facilityForm.setControl('workDays', workDayFormArray);
  }
  

  addDiscipline(): void {
    this.disciplines.push(this.fb.group({
      name: ['', Validators.required]
    }));
  }

  removeDiscipline(index: number): void {
    this.disciplines.removeAt(index);
  }

  addWorkDay(): void {
    this.workDays.push(this.fb.group({
      day: ['', Validators.required],
      fromTime: ['', Validators.required],
      untilTime: ['', Validators.required]
    }));
  }

  removeWorkDay(index: number): void {
    this.workDays.removeAt(index);
  }

  updateFacility(): void {
    if (this.facilityForm.valid) {
      this.facilityService.getFacilityById(this.facilityId).subscribe(existingFacility => {
        const updatedFacility: Facility = {
          ...existingFacility,
          ...this.facilityForm.value,
          active: existingFacility.active !== undefined ? existingFacility.active : true
        };
  
        this.facilityService.updateFacility(this.facilityId, updatedFacility).subscribe(() => {
          this.router.navigate(['/']);
        });
      });
    }
  }
  
}

