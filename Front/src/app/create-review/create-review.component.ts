import { Component, OnInit } from '@angular/core';
import { ReviewService } from './review.service';  // Ensure correct path
import { Review } from '../facilities/facility.model';  // Import the Review interface
import { ActivatedRoute } from '@angular/router';  // Import ActivatedRoute for accessing URL parameters
import { FormsModule } from '@angular/forms';  // Import FormsModule
import { Router } from '@angular/router';  // Import Router for navigation
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-create-review',
  templateUrl: './create-review.component.html',
  styleUrls: ['./create-review.component.css'],
  standalone: true,  // Mark as standalone
  imports: [FormsModule,CommonModule]  // Include FormsModule here
})
export class CreateReviewComponent implements OnInit {
  review: Review = {
    id: 0,
    createdAt: new Date().toISOString(),
    exerciseCount: 1,
    hidden: false,
    rate: {
      id: 0,
      equipment: 0,
      staff: 0,
      hygiene: 0,
      space: 0
    },
    userId: 1,
    facilityId: 0
  };

  errorMessage: string = '';

  constructor(private reviewService: ReviewService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.review.facilityId = +params['id'];
    });
  }

  createReview() {
    this.reviewService.createReview(this.review.userId, this.review.facilityId, this.review).subscribe(
      response => {
        console.log('Review created:', response);
        this.router.navigate(['/']);  // Navigate on success
      },
      error => {
        console.error('Error creating review:', error);
        this.errorMessage = 'There was an error creating the review. Please try again.';
      }
    );
  }

  onSubmit() {
    this.createReview();
  }
}
