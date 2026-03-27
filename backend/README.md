# SplitEasy Backend — Java Spring Boot + MySQL

## Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8.0+

## Setup

1. **Create MySQL database:**
```sql
CREATE DATABASE spliteasy_db;
```

2. **Configure database credentials** in `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=your_password
```

3. **Run the application:**
```bash
mvn spring-boot:run
```

The API will start at `http://localhost:8080`

## API Endpoints

### Friends
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/friends/{sessionId}` | Get all friends in session |
| POST | `/api/friends?sessionId=&name=` | Add a friend |
| DELETE | `/api/friends/{id}` | Remove a friend |
| PUT | `/api/friends/{id}/toggle-paid` | Toggle paid status |

### Items
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/items/{sessionId}` | Get all items in session |
| POST | `/api/items?sessionId=&name=&price=` | Add an item |
| DELETE | `/api/items/{id}` | Remove an item |
| PUT | `/api/items/{itemId}/assign/{friendId}` | Assign friend to item |
| DELETE | `/api/items/{itemId}/assign/{friendId}` | Remove friend from item |

### Split Calculations
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/split/equal/{sessionId}?totalBill=` | Calculate equal split |
| GET | `/api/split/dutch/{sessionId}?taxPercent=&tipPercent=` | Calculate dutch split |
| DELETE | `/api/split/reset/{sessionId}` | Reset session |

## Database Tables (auto-created by Hibernate)
- `friends` — id, name, paid_status, session_id
- `items` — id, name, price, session_id
- `item_participants` — item_id, friend_id (join table)
