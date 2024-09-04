import { Component, OnInit } from '@angular/core';
import { ReviewService } from './review.service';  // Ensure correct path
import { Review } from '../facilities/facility.model';  // Import the Review interface
import { ActivatedRoute } from '@angular/router';  // Import ActivatedRoute for accessing URL parameters
import { FormsModule } from '@angular/forms';  // Import FormsModule
import { Router } from '@angular/router';  // Import Router for navigation



@Component({
  selector: 'app-create-review',
  templateUrl: './create-review.component.html',
  styleUrls: ['./create-review.component.css'],
  standalone: true,  // Mark as standalone
  imports: [FormsModule]  // Include FormsModule here
})
export class CreateReviewComponent implements OnInit {
  review: Review = {
    id: 0,
    createdAt: new Date().toISOString(),  // Set current date-time
    exerciseCount: 0,  // Default to 0
    hidden: false,
    rate: {
      id: 0,
      equipment: 0,
      staff: 0,
      hygiene: 0,
      space: 0
    },
    userId: 1,  // Assuming userId is 1 for simplicity; replace with actual user ID logic
    facilityId: 0  // This will be set from the URL parameter
  };

  constructor(private reviewService: ReviewService, private route: ActivatedRoute,private router: Router) {}

  ngOnInit() {
    // Get facilityId from the URL
    this.route.params.subscribe(params => {
      this.review.facilityId = +params['id'];  // Assuming the route has a parameter named 'facilityId'
    });
  }

  createReview() {
    this.reviewService.createReview(this.review.userId, this.review.facilityId, this.review).subscribe(
      response => {
        console.log('Review created:', response);
      },
      error => {
        console.error('Error creating review:', error);
      }
    );
  }

  onSubmit() {
    this.createReview();  // Call the createReview method when the form is submitted
    this.router.navigate(['/']);  // Use this.router to navigate
  }
  
}




