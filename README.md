# 💸 SplitEasy - Expense Sharing App

## 📌 Overview

SplitEasy is a full-stack expense sharing web application that allows users to split bills among friends using two modes:

* Equal Split Mode
* Dutch (Itemized) Split Mode

The application ensures fair and accurate distribution of expenses with real-time calculations.

---

## ⚙️ Prerequisites

Make sure you have installed:

* Node.js (v18+)
* npm
* Java JDK 17+
* Maven
* Git

---

## 🚀 Setup Guide

### 1. Clone Repository

```bash
git clone <your-repo-link>
cd SplitEasy
```

---

### 2. Run Backend (Spring Boot)

```bash
cd backend
mvn spring-boot:run
```

Backend runs on:
http://localhost:8080

---

### 3. Run Frontend

```bash
cd frontend
npm install
npm run dev -- --port 5173
```

Frontend runs on:
http://localhost:5173

---

## 🧠 Design Decisions (Dutch Split Logic)

In Dutch mode, each user is assigned specific items. The total cost of all items is calculated first. Then, tax and tip are distributed proportionally based on each user's contribution to the total item cost. This ensures fairness and guarantees that the sum of all individual payments exactly matches the total bill amount.

---

## ⚠️ Known Issues

* No authentication system implemented
* Data persistence depends on backend configuration (may reset if not connected to external DB)
* Error handling can be improved for edge cases
* UI responsiveness can be enhanced for smaller devices

---

## 🗄️ Database Schema

Located in:
database/schema.sql

---

## 🎥 Demo Video

[https://drive.google.com/file/d/13TQZuRCkaZMtik93XonFBEtXmS-VyRnn/view?usp=drive_link]

---

## ✨ Features

* Add and manage friends
* Equal bill splitting
* Dutch (itemized) expense splitting
* Proportional tax/tip distribution
* Real-time calculation updates
* Clean and responsive UI

---
