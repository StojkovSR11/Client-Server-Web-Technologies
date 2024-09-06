import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ExerciseService } from '../exercise/exercise.service';
import { Exercise } from '../facilities/facility.model';
import { NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http'; // Import this for error handling

@Component({
  selector: 'app-create-exercise',
  templateUrl: './create-exercise.component.html',
  styleUrls: ['./create-exercise.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterOutlet]
})
export class CreateExerciseComponent {
  facilityId: number;
  disciplineId?: number; // Optional discipline ID
  exercise: Exercise = {
    id: 0,
    fromTime: '',
    untilTime: '',
    userId: 1,
    facilityId: 0,
    date: ''
  };
  errorMessage: string | null = null; // Error message to display

  constructor(
    private exerciseService: ExerciseService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.facilityId = Number(this.route.snapshot.paramMap.get('id'));
    this.disciplineId = Number(this.route.snapshot.paramMap.get('disciplineId')); // Get discipline ID from route
    this.exercise.facilityId = this.facilityId;
    if (this.disciplineId) {
      this.exercise.disciplineId = this.disciplineId;
    }
  }

  createExercise(form: NgForm): void {
    if (form.valid) {
      this.exerciseService.createExercise(this.exercise).subscribe(
        () => {
          // On success, navigate to the facility page
          this.router.navigate([`/facilities/${this.facilityId}`]);
        },
        (error: HttpErrorResponse) => {
          // Handle any errors that come from the backend
          if (error.status === 400) {
            this.errorMessage = error.error; // Set error message from backend
          } else {
            this.errorMessage = 'An unexpected error occurred. Please try again.';
          }
        }
      );
    }
  }

  goBack(): void {
    this.router.navigate([`/facilities/${this.facilityId}`]);
  }
}


