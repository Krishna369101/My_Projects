## Task Manager Dashboard (Jira-lite Clone)

- A lightweight task management web application inspired by Jira, built using Python (Flask/FastAPI) for the backend and HTML, CSS, and JavaScript for the frontend.
It allows users to Create, Read, Update, and Delete (CRUD) tasks while organizing them by status (To Do, In Progress, Done).

## 🚀 Project Overview

This project demonstrates a complete end-to-end web app architecture — from planning and development to database design and API integration — following industry-standard software engineering practices.

### You can use it to:

- Understand how full-stack CRUD apps are structured.

- Learn proper project folder organization, Git usage, and RESTful design.

- Deploy a personal task manager or enhance it into your own productivity tool.

## 🧱 Tech Stack
- Layer	Technology	Purpose
-Frontend	HTML, CSS, JavaScript	UI for task management
-Backend	Python (Flask / FastAPI)	API + business logic
-Database	SQLite (local) / PostgreSQL (optional)	Task persistence
-Version Control	Git / GitHub	Source control
-Deployment (optional)	Docker	Containerized app deployment

## 🗂️ Project Structure
"""
task-manager/ 
├── frontend/
│   ├── index.html
│   ├── styles/
│   │   └── main.css
│   └── scripts/
│       └── main.js
│
├── backend/
│   ├── app/
│   │   ├── __init__.py
│   │   ├── main.py
│   │   ├── routes/
│   │   │   └── task_routes.py
│   │   ├── models/
│   │   │   └── task.py
│   │   ├── db/
│   │   │   └── connection.py
│   │   └── utils/
│   │       └── response.py
│   ├── requirements.txt
│   ├── .gitignore
│   └── README.md
│
└── README.md
"""

## 🧩 Features

✅ Create new tasks with title and description
✅ View all tasks grouped by status
✅ Update task details and status (To Do → In Progress → Done)
✅ Delete completed or unwanted tasks
✅ RESTful API integration between frontend and backend
✅ Responsive, clean UI layout
✅ Modular project structure and maintainable codebase


🧑‍💻 Author

Muthu Krishnan
🚀 Software Engineer | Building from the ground up