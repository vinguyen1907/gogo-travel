insert into public.room (id, base_fare, discount, is_available, max_guests, name, service_fee, tax, type, stay_id)
values  ('1', 100, 10, true, 2, 'Deluxe Room', 15, 0.05, 'Single', '1'),
        ('2', 150, 20, true, 4, 'Suite Room', 20, 0.1, 'Double', '2'),
        ('3', 200, 15, true, 6, 'Family Room', 25, 0.08, 'Family', '3'),
        ('4', 120, 5, false, 2, 'Superior Room', 18, 0.07, 'Single', '4'),
        ('5', 250, 30, true, 4, 'Premium Suite', 30, 0.12, 'Suite', '5'),
        ('6', 80, 0, true, 1, 'Economy Room', 10, 0.05, 'Single', '6'),
        ('7', 220, 25, false, 4, 'Ocean View Room', 28, 0.09, 'Double', '3'),
        ('8', 300, 40, true, 5, 'Executive Suite', 35, 0.15, 'Suite', '8'),
        ('9', 110, 10, true, 2, 'Classic Room', 12, 0.06, 'Single', '7'),
        ('10', 500, 50, false, 8, 'Presidential Suite', 50, 0.2, 'Suite', '10'),
        ('11', 250, 30, true, 4, 'Premium Suite 2', 30, 0.12, 'Suite', '5');