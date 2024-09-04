import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from '../facilities/facility.model';  // Ensure correct path

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private apiUrl = 'http://localhost:8080/reviews';  // Adjust the URL as needed

  constructor(private http: HttpClient) {}

  getAllReviews(): Observable<Review[]> {
    return this.http.get<Review[]>(this.apiUrl);
  }

  getReviewById(id: number): Observable<Review> {
    return this.http.get<Review>(`${this.apiUrl}/${id}`);
  }

  createReview(userId: number, facilityId: number, review: Review): Observable<Review> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Review>(`${this.apiUrl}?userId=${userId}&facilityId=${facilityId}`, review, { headers });
  }

  updateReview(id: number, userId: number, facilityId: number, review: Review): Observable<Review> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Review>(`${this.apiUrl}/${id}?userId=${userId}&facilityId=${facilityId}`, review, { headers });
  }

  deleteReview(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}


