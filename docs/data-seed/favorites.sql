INSERT INTO flight_favorite (id,user_id, outbound_id, return_id) VALUES
-- Round-trip favorites
(1, 2, '11', '12'), -- User 2: Hanoi to Ho Chi Minh (outbound), Ho Chi Minh to Da Nang (return)
(2, 3, '15', '16'), -- User 3: Hanoi to Da Nang (outbound), Ho Chi Minh to Hue (return)
(3, 2, '20', '21'), -- User 2: Ho Chi Minh to Tokyo (outbound), Tokyo to Osaka (return)
(4, 3, '25', '26'), -- User 3: Ho Chi Minh to Nha Trang (outbound), Nha Trang to Da Nang (return)

-- One-way favorites
(5, 2, '13', NULL), -- User 2: Singapore to Kuala Lumpur (one-way)
(6, 3, '18', NULL), -- User 3: Ho Chi Minh to Hanoi (one-way)
(7, 2, '22', NULL), -- User 2: Singapore to Ho Chi Minh (one-way)
(8, 3, '27', NULL);

-- Insert mock data into stay_favorite
INSERT INTO stay_favorite (id, user_id, stay_id) VALUES
(1, 2, '1'), -- User 2 favorites Hanoi hotel
(2, 3, '2'), -- User 3 favorites Ho Chi Minh hotel
(3, 2, '3'), -- User 2 favorites Da Nang resort
(4, 3, '4'), -- User 3 favorites Phu Quoc resort
(5, 2, '5'), -- User 2 favorites Hoi An hotel
(6, 3, '6'), -- User 3 favorites Hue motel
(7, 2, '8'), -- User 2 favorites New York hotel
(8, 3, '9'), -- User 3 favorites Paris hotel
(9, 2, '10'); -- User 2 favorites Dubai resort
