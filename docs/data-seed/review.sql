-- Insert mock data into reviews table
INSERT INTO public.reviews (id, service_id, user_id, rating, description, service_type) VALUES
(UUID(), 31, 2, 5, 'Great service!', 'AIRLINE'),
(UUID(), 32, 3, 4, 'Good service.', 'AIRLINE'),
(UUID(), 33, 2, 3, 'Average experience.', 'AIRLINE'),
(UUID(), 34, 3, 2, 'Not satisfied.', 'AIRLINE'),
(UUID(), 35, 2, 5, 'Excellent!', 'AIRLINE');
