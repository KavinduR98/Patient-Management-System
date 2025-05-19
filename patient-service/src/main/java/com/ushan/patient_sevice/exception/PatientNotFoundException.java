package com.ushan.patient_sevice.exception;

public class PatientNotFoundException extends RuntimeException {
  public PatientNotFoundException(String message) {
    super(message);
  }
}
