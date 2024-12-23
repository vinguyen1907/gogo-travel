insert into public.service (id, name) values
('11', 'VN123'),
('12', 'QH456'),
('13', '3K789'),
('14', 'SQ100'),
('15', 'VN789'),
('16', 'QH101'),
('17', 'PA202'),
('18', '3K303'),
('19', 'SQ404'),
('20', 'VN555'),
('21', 'QH606'),
('22', 'PA707'),
('23', '3K808'),
('24', 'SQ909'),
('25', 'VN111'),
('26', 'QH222'),
('27', 'PA333'),
('28', '3K444'),
('29', 'SQ555');

INSERT INTO public.airline (id, name, image) VALUES
('1', 'Vietnam Airlines', 'https://careerfinder.vn/wp-content/uploads/2020/05/vietnam-airline-logo.jpg'),
('2', 'Bamboo Airways', 'https://static.wikia.nocookie.net/logos/images/f/fd/Bamboo_Airways_logo_2017_-_2018.png/revision/latest?cb=20210909225244&path-prefix=vi'),
('3', 'Pacific Airlines', 'https://s.pilotsglobal.com/img/5/f/7/8/7/5f7875c55e63384bd80c51c49357ebf6.svg'),
('4', 'AirAsia', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/AirAsia_New_Logo.svg/300px-AirAsia_New_Logo.svg.png'),
('5', 'Singapore Airlines', 'https://www.cdnlogo.com/logos/s/19/singapore-airlines.svg');

INSERT INTO public.airport (id, code, name, location_id) VALUES
    ('1', 'HAN', 'Noi Bai International Airport', '1'), -- Hà Nội
    ('2', 'SGN', 'Tan Son Nhat International Airport', '2'), -- Hồ Chí Minh
    ('3', 'DAD', 'Da Nang International Airport', '3'), -- Đà Nẵng
    ('4', 'CXR', 'Cam Ranh International Airport', '4'), -- Nha Trang
    ('5', 'HUI', 'Phu Bai International Airport', '5'), -- Huế
    ('6', 'NRT', 'Narita International Airport', '11'), -- Tokyo, Japan
    ('7', 'CDG', 'Charles de Gaulle Airport', '12'), -- Paris, France
    ('8', 'JFK', 'John F. Kennedy International Airport', '13'), -- New York, USA
    ('9', 'LHR', 'Heathrow Airport', '14'), -- London, UK
    ('10', 'SYD', 'Sydney Kingsford Smith Airport', '15'), -- Sydney, Australia
    ('11', 'ICN', 'Incheon International Airport', '16'), -- Seoul, South Korea
    ('12', 'BKK', 'Suvarnabhumi Airport', '17'), -- Bangkok, Thailand
    ('13', 'SIN', 'Changi Airport', '18'), -- Singapore
    ('14', 'DXB', 'Dubai International Airport', '19'), -- Dubai, UAE
    ('15', 'BCN', 'Barcelona–El Prat Airport', '20'); -- Barcelona, Spain

INSERT INTO public.flight (id, airline_id, departure_airport_id, arrival_airport_id, gate, timezone, departure_time, arrival_time) VALUES
('11', '1', '1', '2', 'A12', 'Asia/Ho_Chi_Minh', '2024-12-25 08:00:00', '2024-12-25 10:30:00'),
('12', '2', '2', '3', 'B5', 'Asia/Ho_Chi_Minh', '2024-12-26 14:00:00', '2024-12-26 15:45:00'),
('13', '4', '3', '6', 'C7', 'Asia/Singapore', '2024-12-27 09:00:00', '2024-12-27 12:00:00'),
('14', '5', '6', '7', 'D10', 'Asia/Tokyo', '2024-12-28 22:00:00', '2024-12-29 06:00:00'),
('15', '1', '1', '3', 'A1', 'Asia/Ho_Chi_Minh', '2024-12-25 12:00:00', '2024-12-25 14:00:00'),
('16', '2', '2', '4', 'B2', 'Asia/Ho_Chi_Minh', '2024-12-26 08:30:00', '2024-12-26 10:45:00'),
('17', '3', '4', '6', 'C8', 'Asia/Singapore', '2024-12-27 18:00:00', '2024-12-27 21:00:00'),
('18', '4', '3', '2', 'D3', 'Asia/Ho_Chi_Minh', '2024-12-28 10:30:00', '2024-12-28 12:00:00'),
('19', '5', '6', '1', 'E5', 'Asia/Tokyo', '2024-12-29 23:00:00', '2024-12-30 06:30:00'),
('20', '1', '2', '7', 'A15', 'Asia/Ho_Chi_Minh', '2024-12-30 07:00:00', '2024-12-30 10:00:00'),
('21', '2', '7', '8', 'B6', 'Asia/Ho_Chi_Minh', '2024-12-31 09:00:00', '2024-12-31 11:15:00'),
('22', '3', '8', '4', 'C10', 'Asia/Singapore', '2024-12-31 13:00:00', '2024-12-31 15:30:00'),
('23', '4', '9', '10', 'D12', 'Asia/Ho_Chi_Minh', '2025-01-01 08:00:00', '2025-01-01 12:00:00'),
('24', '5', '10', '6', 'E3', 'Asia/Tokyo', '2025-01-01 22:00:00', '2025-01-02 06:00:00'),
('25', '1', '2', '9', 'A4', 'Asia/Ho_Chi_Minh', '2025-01-02 06:00:00', '2025-01-02 07:45:00'),
('26', '2', '3', '7', 'B3', 'Asia/Ho_Chi_Minh', '2025-01-02 15:00:00', '2025-01-02 17:30:00'),
('27', '3', '8', '1', 'C2', 'Asia/Singapore', '2025-01-03 12:00:00', '2025-01-03 14:30:00'),
('28', '4', '9', '3', 'D1', 'Asia/Ho_Chi_Minh', '2025-01-03 08:00:00', '2025-01-03 11:00:00'),
('29', '5', '7', '2', 'E8', 'Asia/Tokyo', '2025-01-04 21:00:00', '2025-01-05 05:00:00');


INSERT INTO public.seat (id, flight_id, number, seat_class, base_fare, service_fare, is_available) VALUES
('1', '11', '1A', 1, 100.00, 15.00, true),
('2', '11', '1B', 1, 100.00, 15.00, true),
('3', '11', '2A', 2, 50.00, 10.00, true),
('4', '11', '2B', 2, 50.00, 10.00, false),
('5', '12', '1A', 1, 120.00, 20.00, true),
('6', '12', '1B', 1, 120.00, 20.00, true),
('7', '12', '2A', 2, 60.00, 12.00, true),
('8', '12', '2B', 2, 60.00, 12.00, true),
('9', '13', '3A', 2, 70.00, 14.00, true),
('10', '13', '3B', 2, 70.00, 14.00, true),
('11', '14', '4A', 3, 200.00, 30.00, true),
('12', '14', '4B', 3, 200.00, 30.00, true),
('13', '15', '1A', 2, 1200000, 150000, true),
('14', '15', '1B', 2, 1200000, 150000, true),
('15', '15', '1C', 2, 1200000, 150000, false),
('16', '15', '2A', 1, 3000000, 300000, true),
('17', '15', '2B', 1, 3000000, 300000, false),
('18', '16', '1A', 2, 1500000, 200000, true),
('19', '16', '1B', 2, 1500000, 200000, true),
('20', '16', '2A', 1, 3500000, 400000, false),
('21', '16', '2B', 1, 3500000, 400000, true),
('22', '17', '10A', 0, 900000, 100000, true),
('23', '17', '10B', 0, 900000, 100000, true),
('24', '17', '10C', 0, 900000, 100000, true),
('25', '17', '11A', 0, 900000, 100000, true),
('26', '17', '11B', 0, 900000, 100000, false),
('27', '18', '1A', 2, 1400000, 170000, true),
('28', '18', '1B', 2, 1400000, 170000, true),
('29', '18', '2A', 1, 3200000, 400000, true),
('30', '18', '2B', 1, 3200000, 400000, true),
('31', '19', '1A', 0, 1000000, 120000, true),
('32', '19', '1B', 0, 1000000, 120000, true),
('33', '19', '2A', 1, 4000000, 500000, false),
('34', '19', '2B', 1, 4000000, 500000, true),
('35', '20', '1A', 0, 800000, 100000, true),
('36', '20', '1B', 0, 800000, 100000, true),
('37', '20', '1C', 0, 800000, 100000, true),
('38', '20', '2A', 1, 2500000, 300000, true),
('39', '20', '2B', 1, 2500000, 300000, true);



INSERT INTO public.policy (id, content, airline_id) VALUES
('1', 'Baggage allowance: 30kg per passenger.', '1'),
('2', 'In-flight meals provided.', '2'),
('3', 'Priority boarding for business class.', '3'),
('4', 'Free cancellation within 24 hours.', '4'),
('5', 'Complimentary lounge access for first-class.', '5');
