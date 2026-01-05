AgroNear – Backend Service

AgroNear Backend is a server-side application designed to handle authentication, product management, and secure communication for the AgroNear Android application.

🚨 Problem Statement

A reliable and secure backend system is required to manage users, products, and business logic while ensuring role-based access and data integrity for a farmer–buyer marketplace.

✨ Key Features

User registration and login

JWT-based authentication and authorization

Role-based access for Farmer and Buyer

Product upload and product retrieval APIs

Wishlist management APIs

⚙️ How It Works

User authenticates and receives a JWT token

Token is validated for every protected API request

Backend enforces role-based access control

Product and wishlist data is stored in the database

🛠️ Tech Stack

Kotlin

Spring Boot

Spring Security

JWT Authentication

MySQL

JPA / Hibernate

📦 Current Status

Backend APIs are fully implemented and tested using Postman.
