export interface Rate {
  id: number;
  equipment: number;
  staff: number;
  hygiene: number;
  space: number;
}

export interface Review {
  id: number;
  createdAt: string; // Adjusted to string for date formatting
  exerciseCount: number;
  hidden: boolean;
  rate: Rate; // Nested Rate interface
  userId: number;
  facilityId: number;
}

export interface WorkDay {
  id: number;
  validFrom: string; // Adjusted to string for date formatting
  day: string;
  fromTime: string; // Adjusted to string for time formatting
  untilTime: string; // Adjusted to string for time formatting
}

export interface Discipline {
  id: number;
  name: string;
}

export interface Facility {
  id: number;
  name: string;
  description?: string;  // Optional field
  createdAt: string; // Adjusted to string for date formatting
  address: string;
  city: string;
  totalRating?: number;  // Optional field
  active: boolean;
  workDays: WorkDay[]; // Array of WorkDay
  disciplines: Discipline[]; // Array of Discipline
  reviews: Review[]; // Array of Review
}

export interface Exercise {
  id: number;
  fromTime: string;   // Using string to represent LocalTime in ISO 8601 format
  untilTime: string;  // Using string to represent LocalTime in ISO 8601 format
  userId: number;     // Serialized userId instead of the full User object
  facilityId: number; // Assuming you'll include the facility ID in the frontend model
  disciplineId?: number; // Optional discipline ID
  date: string;
}



  