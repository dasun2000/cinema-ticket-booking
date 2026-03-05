// API Configuration - Microservices Base URLs
const GATEWAY_URL = 'http://localhost:8080';
const API = {
    USER_SERVICE: GATEWAY_URL,
    MOVIE_SERVICE: GATEWAY_URL,
    THEATER_SERVICE: GATEWAY_URL,
    SHOWTIME_SERVICE: GATEWAY_URL,
    BOOKING_SERVICE: GATEWAY_URL,
    SEAT_SERVICE: GATEWAY_URL,

    ADMIN_SERVICE: GATEWAY_URL,
    REPORT_SERVICE: GATEWAY_URL,
    PRICING_SERVICE: GATEWAY_URL,
    TICKET_SERVICE: 'http://localhost:8092', // Direct port for ticket service testing
    PROMOTION_SERVICE: GATEWAY_URL,
};

// Generic fetch helper
async function apiFetch(url, options = {}) {
    try {
        const res = await fetch(url, {
            headers: { 'Content-Type': 'application/json', ...options.headers },
            ...options,
        });
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return await res.json();
    } catch (e) {
        console.error('API Error:', url, e.message);
        return null;
    }
}

// ==== Movie Service ====
const MovieAPI = {
    getAll: () => apiFetch(`${API.MOVIE_SERVICE}/api/movies`),
    getById: (id) => apiFetch(`${API.MOVIE_SERVICE}/api/movies/${id}`),
    getNowShowing: () => apiFetch(`${API.MOVIE_SERVICE}/api/movies/now-showing`),
    search: (q) => apiFetch(`${API.MOVIE_SERVICE}/api/movies/search?title=${q}`),
    create: (data) => apiFetch(`${API.MOVIE_SERVICE}/api/movies`, { method: 'POST', body: JSON.stringify(data) }),
    update: (id, data) => apiFetch(`${API.MOVIE_SERVICE}/api/movies/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (id) => apiFetch(`${API.MOVIE_SERVICE}/api/movies/${id}`, { method: 'DELETE' }),
};

// ==== Theater Service ====
const TheaterAPI = {
    getAll: () => apiFetch(`${API.THEATER_SERVICE}/api/theaters`),
    getById: (id) => apiFetch(`${API.THEATER_SERVICE}/api/theaters/${id}`),
    getByCity: (city) => apiFetch(`${API.THEATER_SERVICE}/api/theaters/city/${city}`),
    getActive: () => apiFetch(`${API.THEATER_SERVICE}/api/theaters/active`),
    create: (data) => apiFetch(`${API.THEATER_SERVICE}/api/theaters`, { method: 'POST', body: JSON.stringify(data) }),
    update: (id, data) => apiFetch(`${API.THEATER_SERVICE}/api/theaters/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (id) => apiFetch(`${API.THEATER_SERVICE}/api/theaters/${id}`, { method: 'DELETE' }),
};

// ==== Showtime Service ====
const ShowtimeAPI = {
    getAll: () => apiFetch(`${API.SHOWTIME_SERVICE}/api/showtimes`),
    getById: (id) => apiFetch(`${API.SHOWTIME_SERVICE}/api/showtimes/${id}`),
    getByMovie: (movieId) => apiFetch(`${API.SHOWTIME_SERVICE}/api/showtimes/movie/${movieId}`),
    getByDate: (date) => apiFetch(`${API.SHOWTIME_SERVICE}/api/showtimes/date/${date}`),
    getByMovieAndDate: (movieId, date) => apiFetch(`${API.SHOWTIME_SERVICE}/api/showtimes/movie/${movieId}/date/${date}`),
    create: (data) => apiFetch(`${API.SHOWTIME_SERVICE}/api/showtimes`, { method: 'POST', body: JSON.stringify(data) }),
    update: (id, data) => apiFetch(`${API.SHOWTIME_SERVICE}/api/showtimes/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (id) => apiFetch(`${API.SHOWTIME_SERVICE}/api/showtimes/${id}`, { method: 'DELETE' }),
    reduceSeats: (id, count) => apiFetch(`${API.SHOWTIME_SERVICE}/api/showtimes/${id}/reduce-seats/${count}`, { method: 'PATCH' }),
};

// ==== Booking Service ====
const BookingAPI = {
    getAll: () => apiFetch(`${API.BOOKING_SERVICE}/api/bookings`),
    getById: (id) => apiFetch(`${API.BOOKING_SERVICE}/api/bookings/${id}`),
    getByUser: (userId) => apiFetch(`${API.BOOKING_SERVICE}/api/bookings/user/${userId}`),
    getByRef: (ref) => apiFetch(`${API.BOOKING_SERVICE}/api/bookings/reference/${ref}`),
    create: (data) => apiFetch(`${API.BOOKING_SERVICE}/api/bookings`, { method: 'POST', body: JSON.stringify(data) }),
    update: (id, data) => apiFetch(`${API.BOOKING_SERVICE}/api/bookings/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    cancel: (id) => apiFetch(`${API.BOOKING_SERVICE}/api/bookings/${id}/cancel`, { method: 'PATCH' }),
    delete: (id) => apiFetch(`${API.BOOKING_SERVICE}/api/bookings/${id}`, { method: 'DELETE' }),
};

// ==== Seat Service ====
const SeatAPI = {
    getAll: () => apiFetch(`${API.SEAT_SERVICE}/api/seats`),
    getById: (id) => apiFetch(`${API.SEAT_SERVICE}/api/seats/${id}`),
    getByShowtime: (showtimeId) => apiFetch(`${API.SEAT_SERVICE}/api/seats/showtime/${showtimeId}`),
    getAvailable: (showtimeId) => apiFetch(`${API.SEAT_SERVICE}/api/seats/showtime/${showtimeId}/available`),
    create: (data) => apiFetch(`${API.SEAT_SERVICE}/api/seats`, { method: 'POST', body: JSON.stringify(data) }),
    update: (id, data) => apiFetch(`${API.SEAT_SERVICE}/api/seats/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (id) => apiFetch(`${API.SEAT_SERVICE}/api/seats/${id}`, { method: 'DELETE' }),
    book: (seatId, bookingId) => apiFetch(`${API.SEAT_SERVICE}/api/seats/${seatId}/book/${bookingId}`, { method: 'PATCH' }),
};

// ==== User Service ====
const UserAPI = {
    getAll: () => apiFetch(`${API.USER_SERVICE}/api/users`),
    getById: (id) => apiFetch(`${API.USER_SERVICE}/api/users/${id}`),
    create: (data) => apiFetch(`${API.USER_SERVICE}/api/users`, { method: 'POST', body: JSON.stringify(data) }),
    update: (id, data) => apiFetch(`${API.USER_SERVICE}/api/users/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (id) => apiFetch(`${API.USER_SERVICE}/api/users/${id}`, { method: 'DELETE' }),
    findByEmail: (email) => apiFetch(`${API.USER_SERVICE}/api/users/email/${email}`),
};


// ==== Admin Service ====
const AdminAPI = {
    login: (email, password) => apiFetch(`${API.ADMIN_SERVICE}/api/admin/login`, {
        method: 'POST',
        body: JSON.stringify({ email, password })
    }),
    logout: () => apiFetch(`${API.ADMIN_SERVICE}/api/admin/logout`, { method: 'POST' }),
    getAll: () => apiFetch(`${API.ADMIN_SERVICE}/api/admin`),
    create: (data) => apiFetch(`${API.ADMIN_SERVICE}/api/admin`, { method: 'POST', body: JSON.stringify(data) }),
    update: (id, data) => apiFetch(`${API.ADMIN_SERVICE}/api/admin/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (id) => apiFetch(`${API.ADMIN_SERVICE}/api/admin/${id}`, { method: 'DELETE' }),
};


// ==== Report Service ====
const ReportAPI = {
    getAll: () => apiFetch(`${API.REPORT_SERVICE}/api/reports`),
    getById: (id) => apiFetch(`${API.REPORT_SERVICE}/api/reports/${id}`),
    create: (data) => apiFetch(`${API.REPORT_SERVICE}/api/reports`, { method: 'POST', body: JSON.stringify(data) }),
    update: (id, data) => apiFetch(`${API.REPORT_SERVICE}/api/reports/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    updatePartial: (id, data) => apiFetch(`${API.REPORT_SERVICE}/api/reports/${id}`, { method: 'PATCH', body: JSON.stringify(data) }),
    delete: (id) => apiFetch(`${API.REPORT_SERVICE}/api/reports/${id}`, { method: 'DELETE' }),
    getByType: (type) => apiFetch(`${API.REPORT_SERVICE}/api/reports/type/${type}`),
};

// ==== Pricing Service ====
const PricingAPI = {
    getAll: () => apiFetch(`${API.PRICING_SERVICE}/api/pricing`),
    getById: (id) => apiFetch(`${API.PRICING_SERVICE}/api/pricing/${id}`),
    getByName: (name) => apiFetch(`${API.PRICING_SERVICE}/api/pricing/name/${name}`),
    create: (data) => apiFetch(`${API.PRICING_SERVICE}/api/pricing/create`, { method: 'POST', body: JSON.stringify(data) }),
    update: (id, data) => apiFetch(`${API.PRICING_SERVICE}/api/pricing/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (id) => apiFetch(`${API.PRICING_SERVICE}/api/pricing/${id}`, { method: 'DELETE' }),
};

// ==== Ticket Service ====
const TicketAPI = {
    getAll: () => apiFetch(`${API.TICKET_SERVICE}/api/tickets`),
    getById: (id) => apiFetch(`${API.TICKET_SERVICE}/api/tickets/${id}`),
    getByBooking: (ref) => apiFetch(`${API.TICKET_SERVICE}/api/tickets/booking/${ref}`),
    getByShowtime: (showtimeId) => apiFetch(`${API.TICKET_SERVICE}/api/tickets/showtime/${showtimeId}`),
    issue: (data) => apiFetch(`${API.TICKET_SERVICE}/api/tickets/issue`, { method: 'POST', body: JSON.stringify(data) }),
    updateStatus: (id, status) => apiFetch(`${API.TICKET_SERVICE}/api/tickets/${id}/status?status=${status}`, { method: 'PUT' }),
    delete: (id) => apiFetch(`${API.TICKET_SERVICE}/api/tickets/${id}`, { method: 'DELETE' }),
};

// ==== Promotion Service ====
const PromotionAPI = {
    getAll: () => apiFetch(`${API.PROMOTION_SERVICE}/api/promotions`),
    getById: (id) => apiFetch(`${API.PROMOTION_SERVICE}/api/promotions/${id}`),
    getActive: () => apiFetch(`${API.PROMOTION_SERVICE}/api/promotions/active`),
    create: (data) => apiFetch(`${API.PROMOTION_SERVICE}/api/promotions`, { method: 'POST', body: JSON.stringify(data) }),
    update: (id, data) => apiFetch(`${API.PROMOTION_SERVICE}/api/promotions/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (id) => apiFetch(`${API.PROMOTION_SERVICE}/api/promotions/${id}`, { method: 'DELETE' }),
    validate: (code) => apiFetch(`${API.PROMOTION_SERVICE}/api/promotions/validate?code=${code}`),
};

// Session helpers
const Session = {
    setAdmin: (data) => localStorage.setItem('adminSession', JSON.stringify(data)),
    getAdmin: () => { try { return JSON.parse(localStorage.getItem('adminSession')); } catch (e) { return null; } },
    clearAdmin: () => localStorage.removeItem('adminSession'),
    isAdminLoggedIn: () => !!localStorage.getItem('adminSession'),
};
