package com.uit.se.gogo.constant;

import com.uit.se.gogo.entity.FlightBooking;
import com.uit.se.gogo.entity.RoomBooking;
import com.uit.se.gogo.entity.SeatBooking;

public class EmailTemplates {
    private static String companyName = "GOGO-Travel";
    public static String generateResetPasswordEmail(String otp, String userName) {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Reset Password OTP</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 0;
                    padding: 0;
                    background-color: #f4f4f9;
                }
                .email-container {
                    max-width: 600px;
                    margin: 20px auto;
                    background-color: #ffffff;
                    border-radius: 8px;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                    padding: 20px;
                }
                .header {
                    text-align: center;
                    border-bottom: 1px solid #ddd;
                    padding-bottom: 10px;
                }
                .header h1 {
                    font-size: 24px;
                    margin: 0;
                    color: #333;
                }
                .content {
                    padding: 20px 0;
                }
                .content p {
                    font-size: 16px;
                    color: #555;
                    line-height: 1.5;
                }
                .otp {
                    font-size: 24px;
                    font-weight: bold;
                    color: #2c7dff;
                    text-align: center;
                    margin: 20px 0;
                }
                .footer {
                    text-align: center;
                    border-top: 1px solid #ddd;
                    padding-top: 10px;
                    margin-top: 20px;
                    font-size: 14px;
                    color: #aaa;
                }
            </style>
        </head>
        <body>
            <div class="email-container">
                <div class="header">
                    <h1>Password Reset Request</h1>
                </div>
                <div class="content">
                    <p>Hi %s,</p>
                    <p>We received a request to reset your password. Please use the OTP below to complete the process. This OTP will expire in 5 minutes.</p>
                    <h6 class="otp">%s</h6>
                    <p>If you did not request a password reset, please ignore this email or contact support if you have concerns.</p>
                    <p>Thank you, <br> The %s Team</p>
                </div>
                <div class="footer">
                    <p>&copy; 2025 %s.</p>
                </div>
            </div>
        </body>
        </html>
        """.formatted(userName, otp, companyName, companyName);
    }

    public static String generateFlightBookingEmail(FlightBooking flightBooking) {
        StringBuilder seatDetails = new StringBuilder();
        for (SeatBooking seatBooking : flightBooking.getSeats()) {
            seatDetails.append("""
                <tr>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                </tr>
            """.formatted(seatBooking.getSeat().getId(),
                          seatBooking.getCitizenName(),
                          seatBooking.getCitizenId()));
        }
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Flight Booking Information</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 0;
                    padding: 0;
                    background-color: #f4f4f9;
                }
                .email-container {
                    max-width: 600px;
                    margin: 20px auto;
                    background-color: #ffffff;
                    border-radius: 8px;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                    padding: 20px;
                }
                .header {
                    text-align: center;
                    border-bottom: 1px solid #ddd;
                    padding-bottom: 10px;
                }
                .header h1 {
                    font-size: 24px;
                    margin: 0;
                    color: #333;
                }
                .content {
                    padding: 20px 0;
                }
                .content p {
                    font-size: 16px;
                    color: #555;
                    line-height: 1.5;
                }
                .table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }
                .table th, .table td {
                    border: 1px solid #ddd;
                    padding: 10px;
                    text-align: left;
                }
                .table th {
                    background-color: #f2f2f2;
                }
                .footer {
                    text-align: center;
                    border-top: 1px solid #ddd;
                    padding-top: 10px;
                    margin-top: 20px;
                    font-size: 14px;
                    color: #aaa;
                }
            </style>
        </head>
        <body>
            <div class="email-container">
                <div class="header">
                    <h1>Flight Booking Confirmation</h1>
                </div>
                <div class="content">
                    <p>Dear %s,</p>
                    <p>Thank you for booking your flight with us! Here are your booking details:</p>
                    <p><strong>Booking ID:</strong> %s</p>
                    <p><strong>Status:</strong> %s</p>
                    <p><strong>Booking Date:</strong> %s</p>
                    <p><strong>Update Date:</strong> %s</p>
                    <p><strong>Total Discount:</strong> $%s</p>
                    <p><strong>Total Bill:</strong> $%s</p>
                    <h3>Passenger and Seat Details</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Seat ID</th>
                                <th>Passenger Name</th>
                                <th>Passenger ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            %s
                        </tbody>
                    </table>
                </div>
                <div class="footer">
                    <p>Thank you for choosing our service. Have a great flight!</p>
                    <p>&copy; 2025 %s.  </p>
                </div>
            </div>
        </body>
        </html>
        """.formatted(flightBooking.getUser().getFullName(),
                      flightBooking.getId(),
                      flightBooking.getStatus().name(),
                      flightBooking.getBookingDate(),
                      flightBooking.getUpdateDate(),
                      flightBooking.getTotalDiscount(),
                      flightBooking.getTotalBill(),
                      seatDetails.toString(),
                      companyName);
    }

    public static String generateRoomBookingEmail(RoomBooking roomBooking) {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Room Booking Confirmation</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 0;
                    padding: 0;
                    background-color: #f9f9f9;
                }
                .email-container {
                    max-width: 600px;
                    margin: 20px auto;
                    background-color: #ffffff;
                    border-radius: 8px;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                    padding: 20px;
                }
                .header {
                    text-align: center;
                    border-bottom: 1px solid #ddd;
                    padding-bottom: 10px;
                }
                .header h1 {
                    font-size: 24px;
                    margin: 0;
                    color: #333;
                }
                .content {
                    padding: 20px 0;
                }
                .content p {
                    font-size: 16px;
                    color: #555;
                    line-height: 1.5;
                }
                .table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }
                .table th, .table td {
                    border: 1px solid #ddd;
                    padding: 10px;
                    text-align: left;
                }
                .table th {
                    background-color: #f2f2f2;
                }
                .footer {
                    text-align: center;
                    border-top: 1px solid #ddd;
                    padding-top: 10px;
                    margin-top: 20px;
                    font-size: 14px;
                    color: #aaa;
                }
            </style>
        </head>
        <body>
            <div class="email-container">
                <div class="header">
                    <h1>Room Booking Confirmation</h1>
                </div>
                <div class="content">
                    <p>Dear %s %s,</p>
                    <p>Thank you for booking with us! Here are your booking details:</p>
                    <p><strong>Booking ID:</strong> %s</p>
                    <p><strong>Status:</strong> %s</p>
                    <p><strong>Check-in Date:</strong> %s</p>
                    <p><strong>Check-out Date:</strong> %s</p>
                    <h3>Room Details</h3>
                    <p><strong>Room Name:</strong> %s</p>
                    <p><strong>Type:</strong> %s</p>
                    <p><strong>Base Fare:</strong> $%s</p>
                    <p><strong>Discount:</strong> $%s</p>
                    <p><strong>Tax:</strong> %s%%</p>
                    <p><strong>Service Fee:</strong> $%s</p>
                    <p><strong>Max Guests:</strong> %s</p>
                    <p><strong>Image:</strong> <a href="%s">View Room</a></p>
                    <h3>Guest Information</h3>
                    <p><strong>Email:</strong> %s</p>
                    <p><strong>Phone:</strong> %s</p>
                    <p><strong>Country:</strong> %s</p>
                </div>
                <div class="footer">
                    <p>We look forward to hosting you. Have a wonderful stay!</p>
                    <p>&copy; 2025 %s.  </p>
                </div>
            </div>
        </body>
        </html>
        """.formatted(
                roomBooking.getFirstName(),
                roomBooking.getLastName(),
                roomBooking.getId(),
                roomBooking.getStatus().name(),
                roomBooking.getCheckinDate(),
                roomBooking.getCheckoutDate(),
                roomBooking.getRoom().getName(),
                roomBooking.getRoom().getType(),
                roomBooking.getRoom().getBaseFare(),
                roomBooking.getRoom().getDiscount(),
                roomBooking.getRoom().getTax(),
                roomBooking.getRoom().getServiceFee(),
                roomBooking.getRoom().getMaxGuests(),
                roomBooking.getRoom().getImageUrl(),
                roomBooking.getEmail(),
                roomBooking.getPhone(),
                roomBooking.getCountry(),
                companyName
        );
    }
}
