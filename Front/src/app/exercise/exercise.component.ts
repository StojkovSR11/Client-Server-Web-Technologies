import { Component, OnInit } from '@angular/core';
import { ExerciseService } from './exercise.service';
import { Exercise } from '../facilities/facility.model'; // Adjust path if necessary
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterOutlet]
})
export class ExerciseComponent implements OnInit {
  exercises: Exercise[] = [];
  selectedExercise: Exercise | null = null;

  constructor(private exerciseService: ExerciseService) {}

  ngOnInit(): void {
    this.loadExercises();
  }

  loadExercises(): void {
    this.exerciseService.getAllExercises().subscribe(data => {
      this.exercises = data;
    });
  }

  viewExercise(exercise: Exercise): void {
    this.selectedExercise = exercise;
  }

  clearSelection(): void {
    this.selectedExercise = null;
  }

  deleteExercise(id: number, event: MouseEvent): void {
    event.stopPropagation(); // Prevent the event from triggering viewExercise
    if (confirm('Are you sure you want to delete this exercise?')) {
      this.exerciseService.deleteExercise(id).subscribe(() => {
        this.exercises = this.exercises.filter(exercise => exercise.id !== id);
        this.clearSelection();
      });
    }
  }
}
