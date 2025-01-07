# Fitpass-Inspired Sports and Recreation App

This project aims to develop a sports and recreation application similar to Fitpass, allowing users to discover, book, and review various fitness facilities. The application features user authentication, facility management, booking functionality, review system, and administrative tools.

## Key Features

*   **User Registration and Authentication:** Secure user accounts with username/password and token-based authorization.
*   **Facility Management:** Comprehensive facility information including details, disciplines, operating hours, and manager assignment.
*   **Exercise Booking:** Users can book exercises at facilities within their operating hours.
*   **Review and Rating System:** Users can leave reviews and ratings for facilities, and facility managers can respond.
*   **Search and Filtering:** Robust search and filtering options for facilities by city, discipline, rating, and operating hours.
*   **User Profiles:** Users can manage their profile information and view their booking history.

## Architecture

The application follows a three-tier architecture:

*   **Frontend:** Angular web application.
*   **Backend:** Spring Boot application with RESTful APIs.
*   **Database:** MySQL or PostgreSQL.

## Technologies Used

*   Spring Framework, Spring Boot
*   Angular
*   MySQL

## Data Model

The application uses a relational database with entities such as User, Facility, Discipline, Exercise, Review, and Rate.

## Non-Functional Requirements

*   Secure authentication and authorization.
