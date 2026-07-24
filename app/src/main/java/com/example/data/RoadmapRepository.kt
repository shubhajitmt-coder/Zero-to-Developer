package com.example.data

import kotlinx.coroutines.flow.Flow

class RoadmapRepository(private val db: AppDatabase) {

    val taskProgressFlow: Flow<List<TaskProgress>> = db.taskDao().getAllTaskProgress()
    val dsaProgressFlow: Flow<List<DsaProgress>> = db.dsaDao().getAllDsaProgress()

    suspend fun toggleTaskCompletion(taskId: String, currentStatus: Boolean) {
        db.taskDao().insertOrUpdateTask(TaskProgress(taskId = taskId, isCompleted = !currentStatus))
    }

    suspend fun updateDsaSolved(patternId: String, count: Int, target: Int) {
        db.dsaDao().insertOrUpdateDsa(DsaProgress(patternId = patternId, solvedCount = count, targetCount = target, isMastered = count >= target))
    }

    // --- 1. CAREER PATH COMPARISON DATA (10 TECH PATHS FOR INDIAN B.SC CS) ---
    fun getCareerPaths(): List<CareerPathModel> {
        return listOf(
            CareerPathModel(
                id = "sde_backend",
                title = "Backend Engineering / SDE",
                description = "Designing high-scale APIs, server architecture, databases, caching, and business logic.",
                fresherOpps = "High",
                avgSalaryFresh = "₹6 - 18 LPA",
                longTermSalary = "₹25 - 75+ LPA",
                difficulty = "4/5",
                competition = "High (Requires strong DSA + Systems)",
                mathRequired = "Basic (Discrete Math & Logic)",
                dsaImportance = "Critical (100% evaluated in OAs)",
                bscSuitability = "High (Skills & projects override degree barriers)",
                aiAutomationRisk = "Low (System design & architecture require complex human reasoning)",
                isTop3 = true,
                isRecommendedPrimary = true,
                ranking = 1,
                summaryWhy = "Highest availability of high-paying fresher off-campus hiring, strong mobility, and foundational for all software engineering roles."
            ),
            CareerPathModel(
                id = "fullstack",
                title = "Full-Stack Developer",
                description = "Building both UI (React/Next.js) and Backend APIs (Node/Spring) end-to-end.",
                fresherOpps = "Very High (Startups & SMEs)",
                avgSalaryFresh = "₹4 - 12 LPA",
                longTermSalary = "₹18 - 45+ LPA",
                difficulty = "3.5/5",
                competition = "Very High (Saturated at beginner level)",
                mathRequired = "Basic",
                dsaImportance = "High",
                bscSuitability = "Very High (Highly valued in startups)",
                aiAutomationRisk = "Medium (Basic frontend components are heavily automated)",
                isTop3 = true,
                isRecommendedPrimary = false,
                ranking = 2,
                summaryWhy = "Great for getting early internship experience at startups, but backend depth is required to cross ₹12+ LPA."
            ),
            CareerPathModel(
                id = "devops_cloud",
                title = "Cloud & DevOps / SRE",
                description = "Infrastructure, CI/CD pipelines, Kubernetes, cloud security, and system reliability.",
                fresherOpps = "Moderate",
                avgSalaryFresh = "₹6 - 15 LPA",
                longTermSalary = "₹22 - 60+ LPA",
                difficulty = "4/5",
                competition = "Moderate",
                mathRequired = "Basic",
                dsaImportance = "Moderate (Scripting + Linux + OS matters more)",
                bscSuitability = "High (Certifications + Hands-on Linux/Docker gives direct edge)",
                aiAutomationRisk = "Low",
                isTop3 = true,
                isRecommendedPrimary = false,
                ranking = 3,
                summaryWhy = "Excellent field with high growth and lower pure DSA competition, but requires production system exposure."
            ),
            CareerPathModel(
                id = "ai_ml",
                title = "AI / ML Engineer",
                description = "Building, fine-tuning, and deploying machine learning models and neural networks.",
                fresherOpps = "Low (Highly favours M.Tech / Ph.D / B.Tech Tier 1)",
                avgSalaryFresh = "₹8 - 20 LPA",
                longTermSalary = "₹30 - 80+ LPA",
                difficulty = "5/5",
                competition = "Extremely High",
                mathRequired = "Advanced (Linear Algebra, Calculus, Probability, Stats)",
                dsaImportance = "High",
                bscSuitability = "Low for freshers (High degree barrier for pure ML engineering)",
                aiAutomationRisk = "Low",
                isTop3 = false,
                ranking = 4,
                summaryWhy = "High hype but extremely tough entry for B.Sc freshers. Better pursued as AI-Enabled SDE first, then specializing later."
            ),
            CareerPathModel(
                id = "data_engineer",
                title = "Data Engineer",
                description = "Building ETL pipelines, data warehouses, Spark, Kafka, and big-data processing.",
                fresherOpps = "Moderate",
                avgSalaryFresh = "₹6 - 14 LPA",
                longTermSalary = "₹20 - 50+ LPA",
                difficulty = "4/5",
                competition = "Moderate",
                mathRequired = "Moderate",
                dsaImportance = "High (SQL + Python/Scala + Distributed Systems)",
                bscSuitability = "Moderate-High",
                aiAutomationRisk = "Low",
                isTop3 = false,
                ranking = 5,
                summaryWhy = "Strong career path with solid pay, but requires heavy SQL, Spark, and Distributed Storage knowledge."
            ),
            CareerPathModel(
                id = "mobile_dev",
                title = "Mobile App Developer (Android/iOS)",
                description = "Building native mobile applications using Kotlin/Jetpack Compose or Swift.",
                fresherOpps = "Moderate-High",
                avgSalaryFresh = "₹5 - 12 LPA",
                longTermSalary = "₹18 - 45+ LPA",
                difficulty = "3/5",
                competition = "Moderate",
                mathRequired = "Basic",
                dsaImportance = "Moderate-High",
                bscSuitability = "High (Visible apps on Play Store speak louder than degrees)",
                aiAutomationRisk = "Medium",
                isTop3 = false,
                ranking = 6,
                summaryWhy = "Great practical domain. Publishing published apps provides strong portfolio proof."
            ),
            CareerPathModel(
                id = "cybersecurity",
                title = "Cybersecurity Engineer",
                description = "Penetration testing, application security, network defense, and SOC analysis.",
                fresherOpps = "Low (Mostly analyst / SOC roles initially)",
                avgSalaryFresh = "₹4 - 10 LPA",
                longTermSalary = "₹18 - 50+ LPA",
                difficulty = "4.5/5",
                competition = "Moderate",
                mathRequired = "Moderate (Cryptography)",
                dsaImportance = "Low-Moderate",
                bscSuitability = "Moderate (Requires OSCP/CEH certifications for industry entry)",
                aiAutomationRisk = "Low",
                isTop3 = false,
                ranking = 7,
                summaryWhy = "High security demand, but fresher roles are often limited to lower-paid security monitoring analyst positions."
            ),
            CareerPathModel(
                id = "data_analyst",
                title = "Data Analyst / Data Scientist",
                description = "Analyzing datasets using SQL, Python, Pandas, Tableau, and statistical tests.",
                fresherOpps = "High (Analyst) / Low (Data Scientist)",
                avgSalaryFresh = "₹3.5 - 8 LPA",
                longTermSalary = "₹15 - 35 LPA",
                difficulty = "2.5/5",
                competition = "Extremely High (Low barrier to entry, flooded by bootcamps)",
                mathRequired = "High (Statistics & Probability)",
                dsaImportance = "Low (SQL + Analytics)",
                bscSuitability = "High for Analyst roles",
                aiAutomationRisk = "High (Basic data parsing & SQL generation is easily automated)",
                isTop3 = false,
                ranking = 8,
                summaryWhy = "Easy to enter as Data Analyst, but lower salary ceiling and higher automation risk compared to SDE."
            ),
            CareerPathModel(
                id = "qa_automation",
                title = "QA & Automation Testing",
                description = "Writing automated test scripts using Selenium, Cypress, JUnit, and Postman.",
                fresherOpps = "High",
                avgSalaryFresh = "₹3.5 - 7 LPA",
                longTermSalary = "₹12 - 25 LPA",
                difficulty = "2/5",
                competition = "High",
                mathRequired = "Basic",
                dsaImportance = "Low",
                bscSuitability = "High",
                aiAutomationRisk = "High",
                isTop3 = false,
                ranking = 9,
                summaryWhy = "Lower salary trajectory and higher risk of role compression due to AI test script generators."
            ),
            CareerPathModel(
                id = "ui_ux_frontend",
                title = "Pure Frontend Developer",
                description = "Creating web UI interfaces using React/HTML/CSS without deep backend.",
                fresherOpps = "Moderate",
                avgSalaryFresh = "₹3.5 - 8 LPA",
                longTermSalary = "₹14 - 30 LPA",
                difficulty = "2.5/5",
                competition = "Extremely High",
                mathRequired = "Basic",
                dsaImportance = "Low-Moderate",
                bscSuitability = "High",
                aiAutomationRisk = "High (UI code generation is very advanced)",
                isTop3 = false,
                ranking = 10,
                summaryWhy = "Pure UI development without backend systems or DSA knowledge leaves candidates vulnerable to market saturation."
            )
        )
    }

    // --- 2. 24-MONTH MASTER ROADMAP DATA ---
    fun get24MonthRoadmap(): List<MonthlyStageModel> {
        return listOf(
            MonthlyStageModel(
                month = 1,
                quarter = "Q1",
                year = 1,
                title = "Core Programming Mastery & C++ / Java Transition",
                focusArea = "Language Deep Dive & Memory Mental Model",
                whatToLearn = listOf(
                    "Transition from C to C++ (or Java): OOP basics, STL (vector, map, set) / Java Collections Framework",
                    "Pointers vs References, Heap vs Stack memory allocation",
                    "Time and Space Complexity Analysis (Big-O notation, asymptotic analysis)",
                    "Basic Git & GitHub: Init, commit, push, branch, pull request"
                ),
                whyNeeded = "C gives memory fundamentals, but C++/Java provides essential STL/Collections data structures required for speed in DSA interviews.",
                depthLevel = "Deep conceptual understanding of pointers, pass-by-value vs pass-by-reference, and memory layout.",
                prerequisites = "Basic C programming knowledge (loops, functions, arrays).",
                recommendedOrder = listOf("1. C to C++/Java Syntax", "2. STL / Collections", "3. Time Complexity Analysis", "4. Basic Git workflow"),
                whatNotToLearnYet = listOf("Advanced Dynamic Programming", "Spring Boot / Web Frameworks", "Docker", "System Design"),
                practiceRequirements = "Solve 25 beginner array & string mechanics problems in C++/Java on LeetCode/Code360.",
                projectRequirements = "Build a CLI-based Student Management System in C++/Java using OOP concepts and file handling.",
                readinessCheck = listOf(
                    "Can analyze Big-O of nested loops in 10 seconds",
                    "Can use C++ STL vector/map/set (or Java ArrayList/HashMap) without Googling syntax",
                    "GitHub profile created with 1 repository pushed"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m1_t1", "Master C++ STL / Java Collections (vector, map, set, queue)"),
                    RoadmapTaskModel("m1_t2", "Understand Time & Space Complexity (Big-O, Omega, Theta)"),
                    RoadmapTaskModel("m1_t3", "Solve 25 Easy Problems on Arrays & Strings on LeetCode"),
                    RoadmapTaskModel("m1_t4", "Build CLI Student Management System in C++/Java & Push to GitHub")
                )
            ),
            MonthlyStageModel(
                month = 2,
                quarter = "Q1",
                year = 1,
                title = "Arrays, Strings & 2-Pointer / Sliding Window Patterns",
                focusArea = "Pattern-Based Problem Solving",
                whatToLearn = listOf(
                    "Two Pointers Pattern (Opposite Direction, Fast & Slow Pointers)",
                    "Sliding Window Pattern (Fixed Size Window, Variable Size Window)",
                    "Prefix Sum Arrays & Hash Map lookup optimization",
                    "Basic Sorting Algorithms (Merge Sort, Quick Sort theory & recursion base)"
                ),
                whyNeeded = "80% of array/string interview problems are variations of Two Pointers, Sliding Window, or Hash Map lookups.",
                depthLevel = "Pattern recognition level — ability to identify pattern within 2 minutes of reading problem statement.",
                prerequisites = "Month 1 STL/Collections and Big-O analysis.",
                recommendedOrder = listOf("1. Two Pointers", "2. Sliding Window", "3. Prefix Sum & Hashing", "4. Sorting"),
                whatNotToLearnYet = listOf("Dynamic Programming", "Trees & Graphs", "Backend Web frameworks"),
                practiceRequirements = "Solve 30 pattern problems (10 Two Pointers, 10 Sliding Window, 10 Hash Map).",
                projectRequirements = "Enhance CLI tool with searchable indexed log parser script.",
                readinessCheck = listOf(
                    "Can solve 'Two Sum', '3Sum', 'Container With Most Water' without looking at solution",
                    "Can explain Sliding Window fixed vs variable size logic clearly"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m2_t1", "Solve 10 Two Pointer Problems (Opposite & Fast/Slow)"),
                    RoadmapTaskModel("m2_t2", "Solve 10 Sliding Window Problems (Fixed & Variable)"),
                    RoadmapTaskModel("m2_t3", "Solve 10 Hashing & Prefix Sum Problems"),
                    RoadmapTaskModel("m2_t4", "Implement Merge Sort & Quick Sort from scratch in code")
                )
            ),
            MonthlyStageModel(
                month = 3,
                quarter = "Q1",
                year = 1,
                title = "Linked Lists, Stacks, Queues & Recursion Fundamentals",
                focusArea = "Linear Data Structures & Recursion Base",
                whatToLearn = listOf(
                    "Singly & Doubly Linked Lists (Inversion, Fast & Slow pointer for cycle detection)",
                    "Monotonic Stack Pattern (Next Greater Element, Daily Temperatures)",
                    "Queue, Deque, Circular Queue implementation",
                    "Recursion Fundamentals: Recursion tree, call stack memory, base cases"
                ),
                whyNeeded = "Linked Lists and Monotonic Stacks test pointer manipulation and state management in interviews.",
                depthLevel = "Pointer precision — zero null-pointer exceptions or memory leaks.",
                prerequisites = "Pointers/References and Basic Sorting.",
                recommendedOrder = listOf("1. Linked List operations", "2. Fast & Slow Pointer in LL", "3. Monotonic Stack", "4. Recursion Tree model"),
                whatNotToLearnYet = listOf("Trees & Graphs", "DBMS", "System Design"),
                practiceRequirements = "Solve 25 problems (10 Linked Lists, 10 Stacks/Queues, 5 Recursion).",
                projectRequirements = "Implement a custom Undo/Redo & History buffer engine using Stacks & Deque.",
                readinessCheck = listOf(
                    "Can reverse a Linked List iteratively and recursively",
                    "Can solve Next Greater Element using Monotonic Stack in O(N)"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m3_t1", "Master Linked List reversal and cycle detection (Floyd's algorithm)"),
                    RoadmapTaskModel("m3_t2", "Solve 10 Monotonic Stack problems"),
                    RoadmapTaskModel("m3_t3", "Practice recursive call stack tracing on paper"),
                    RoadmapTaskModel("m3_t4", "Milestone 1 Self-Audit (M3 Scorecard Target: >= 70/100)")
                )
            ),
            MonthlyStageModel(
                month = 4,
                quarter = "Q2",
                year = 1,
                title = "Trees, BST & Heaps / Priority Queues",
                focusArea = "Hierarchical Data Structures",
                whatToLearn = listOf(
                    "Binary Tree Traversals (Inorder, Preorder, Postorder - Recursive & Iterative)",
                    "Level Order Traversal (BFS using Queue)",
                    "Binary Search Tree (BST) properties, validation, and LCA",
                    "Heap / Priority Queue (Min-Heap, Max-Heap, Top K Elements pattern)"
                ),
                whyNeeded = "Trees and Heaps form 30%+ of technical interview questions at top product companies.",
                depthLevel = "Tree traversal fluidity — write recursive tree code effortlessly.",
                prerequisites = "Recursion tree mental model and Queue data structures.",
                recommendedOrder = listOf("1. Binary Tree Traversals", "2. Level Order BFS", "3. BST operations", "4. Heaps & Top K pattern"),
                whatNotToLearnYet = listOf("Complex Graph algorithms (Tarjan, Segment Trees)", "Advanced Microservices"),
                practiceRequirements = "Solve 30 tree/heap problems (15 Trees, 10 BST, 5 Heaps).",
                projectRequirements = "Build an File Tree Indexer CLI tool that calculates folder sizes and searches files recursively.",
                readinessCheck = listOf(
                    "Can solve 'Lowest Common Ancestor' and 'Validate BST'",
                    "Can find Kth Largest Element using Min-Heap in O(N log K)"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m4_t1", "Implement Inorder, Preorder, Postorder, and Level Order Traversals"),
                    RoadmapTaskModel("m4_t2", "Solve 10 Tree recursion problems (Max Depth, Diameter, Path Sum)"),
                    RoadmapTaskModel("m4_t3", "Solve 5 Top-K problems using PriorityQueue/Min-Heap"),
                    RoadmapTaskModel("m4_t4", "Build File Tree Indexer CLI tool")
                )
            ),
            MonthlyStageModel(
                month = 5,
                quarter = "Q2",
                year = 1,
                title = "Graphs (BFS/DFS, Topological Sort, Disjoint Set Union)",
                focusArea = "Graph Traversal & Connectivity Patterns",
                whatToLearn = listOf(
                    "Graph Representations: Adjacency List vs Matrix",
                    "BFS (Breadth First Search) & DFS (Depth First Search) for grid and node graphs",
                    "Topological Sort (Kahn's Algorithm & DFS with stack)",
                    "Disjoint Set Union (DSU) / Union-Find with Path Compression",
                    "Shortest Path Basics: Dijkstra's Algorithm"
                ),
                whyNeeded = "Graphs test real-world modeling (social networks, routing, task dependencies) and frequently appear in online assessments.",
                depthLevel = "Pattern mapping — recognize when a problem (matrix, dependencies) is secretly a graph problem.",
                prerequisites = "Recursion, Queues, Tree traversals.",
                recommendedOrder = listOf("1. Adjacency List", "2. BFS/DFS on Grids & Nodes", "3. Topological Sort", "4. DSU", "5. Dijkstra"),
                whatNotToLearnYet = listOf("Advanced Network Flow Algorithms", "Complex Distributed System Consensus"),
                practiceRequirements = "Solve 25 graph problems (10 Grid BFS/DFS, 5 Topological Sort, 5 DSU, 5 Shortest Path).",
                projectRequirements = "Build a Dependency Graph Resolver (like npm/gradle package solver) using Topological Sort.",
                readinessCheck = listOf(
                    "Can solve 'Number of Islands' and 'Course Schedule I & II' cleanly",
                    "Can explain Union-Find with path compression in 2 minutes"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m5_t1", "Master Grid BFS/DFS (Number of Islands, Rotting Oranges)"),
                    RoadmapTaskModel("m5_t2", "Solve Course Schedule I & II using Kahn's Topological Sort"),
                    RoadmapTaskModel("m5_t3", "Implement DSU with Path Compression & Union by Rank"),
                    RoadmapTaskModel("m5_t4", "Implement Dijkstra's Algorithm with Min-Heap")
                )
            ),
            MonthlyStageModel(
                month = 6,
                quarter = "Q2",
                year = 1,
                title = "Dynamic Programming & Core CS Foundations (OOP & SQL)",
                focusArea = "DP Patterns & Database/OOP Foundations",
                whatToLearn = listOf(
                    "Dynamic Programming Core: Memoization (Top-down) vs Tabulation (Bottom-up)",
                    "Standard DP Patterns: 1D DP (Climbing Stairs, House Robber), Knapsack 0/1, Unbounded Knapsack",
                    "OOP Fundamentals: 4 Pillars (Abstraction, Encapsulation, Inheritance, Polymorphism) in Java/C++",
                    "DBMS & SQL Core: ER Diagrams, Normalization (1NF to 3NF), Relational Algebra, SQL Queries (JOINs, Group By, Subqueries)"
                ),
                whyNeeded = "DP separates top candidates in coding tests. OOP and SQL are mandatory technical interview rounds for every SDE job.",
                depthLevel = "High interview fluency — write raw SQL queries with complex JOINs and explain DP state transitions.",
                prerequisites = "Recursion and Basic Arrays/HashMaps.",
                recommendedOrder = listOf("1. 1D DP", "2. Knapsack DP", "3. OOP 4 Pillars with code examples", "4. SQL JOINs & Group By"),
                whatNotToLearnYet = listOf("Microservices Architecture", "Kubernetes"),
                practiceRequirements = "Solve 20 DP problems + Write 30 complex SQL queries on LeetCode Database / HackerRank SQL.",
                projectRequirements = "Design an E-Commerce Database Schema in PostgreSQL with indexing and relational integrity constraints.",
                readinessCheck = listOf(
                    "Can solve 0/1 Knapsack and Coin Change problems",
                    "Can write SQL queries with INNER, LEFT, RIGHT JOINs and GROUP BY HAVING without mistakes",
                    "Milestone 2 Self-Audit (M6 Scorecard Target: >= 75/100)"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m6_t1", "Solve 10 1D & Knapsack DP problems"),
                    RoadmapTaskModel("m6_t2", "Write code demonstrating OOP Encapsulation, Inheritance, and Polymorphism"),
                    RoadmapTaskModel("m6_t3", "Solve 30 SQL query challenges on LeetCode SQL"),
                    RoadmapTaskModel("m6_t4", "Design normalized PostgreSQL E-Commerce schema with ER Diagram")
                )
            ),
            MonthlyStageModel(
                month = 7,
                quarter = "Q3",
                year = 1,
                title = "Backend Development Core (Java + Spring Boot / HTTP / REST APIs)",
                focusArea = "Primary Backend Stack Blueprint",
                whatToLearn = listOf(
                    "Java Backend Ecosystem: JDK 21+, Maven/Gradle, Spring Boot 3 structure",
                    "HTTP Protocol: Methods (GET, POST, PUT, DELETE, PATCH), Headers, Status Codes (2xx, 4xx, 5xx)",
                    "RESTful API Design Principles: Resource naming, payload validation (@Valid), DTO pattern",
                    "Spring Web & Dependency Injection: @RestController, @Service, @Repository, @Autowired / Constructor Injection"
                ),
                whyNeeded = "Java + Spring Boot is the #1 enterprise backend stack in India (used by Amazon, Walmart, PayPal, Swiggy, Zerodha, and thousands of fintechs/startups).",
                depthLevel = "Production-ready REST API design — proper DTO separation, input validation, and clean layered architecture.",
                prerequisites = "Java OOP basics, HTTP concepts, SQL database setup.",
                recommendedOrder = listOf("1. HTTP Fundamentals", "2. Spring Boot Project Setup", "3. Controller-Service-Repository Pattern", "4. Input Validation & Exception Handling"),
                whatNotToLearnYet = listOf("Distributed Caching / Kafka", "Kubernetes", "GraphQL"),
                practiceRequirements = "Build 5 CRUD API endpoints with custom global exception handling (@ControllerAdvice).",
                projectRequirements = "Project 1 (Core): RESTful Task Management & Workspace API with global exception handler and DTO mapper.",
                readinessCheck = listOf(
                    "Can create a Spring Boot REST API from scratch in 15 minutes",
                    "Can explain Dependency Injection and IoC container clearly"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m7_t1", "Setup Java 21 & Spring Boot 3 project with Maven/Gradle"),
                    RoadmapTaskModel("m7_t2", "Build Controller-Service-Repository layers for a resource"),
                    RoadmapTaskModel("m7_t3", "Implement Global Exception Handling (@ControllerAdvice) and Custom Response DTOs"),
                    RoadmapTaskModel("m7_t4", "Test all REST endpoints using Postman / Bruno and document with OpenAPI/Swagger")
                )
            ),
            MonthlyStageModel(
                month = 8,
                quarter = "Q3",
                year = 1,
                title = "Database Persistence (Spring Data JPA / PostgreSQL / ORM)",
                focusArea = "Production Persistence & SQL Integration",
                whatToLearn = listOf(
                    "Spring Data JPA & Hibernate: @Entity, @Table, @Id, @GeneratedValue, @Column",
                    "Entity Relationships: @OneToMany, @ManyToOne, @ManyToMany, FetchType.LAZY vs EAGER",
                    "Database Migrations: Flyway or Liquibase for versioned DB schema migrations",
                    "PostgreSQL Query Optimization: Indexes (B-Tree, Hash), N+1 Select Problem prevention using JOIN FETCH"
                ),
                whyNeeded = "Real-world backend bugs and performance issues stem from improper ORM queries, N+1 problems, and missing DB indexes.",
                depthLevel = "Database tuning level — avoid N+1 queries, tune lazy loading, and write custom JPQL / Native queries.",
                prerequisites = "SQL basics (Month 6) & Spring Boot Core (Month 7).",
                recommendedOrder = listOf("1. JPA Entity Mapping", "2. Spring Data Repositories", "3. Relationship Mapping & LAZY loading", "4. Indexing & N+1 fix"),
                whatNotToLearnYet = listOf("NoSQL Databases (Cassandra, MongoDB) unless needed", "Complex Microservices"),
                practiceRequirements = "Connect Spring Boot to local PostgreSQL instance, write 10 complex JPA repository queries with JPQL.",
                projectRequirements = "Integrate PostgreSQL & Flyway into Project 1, adding pagination, sorting, and database index tuning.",
                readinessCheck = listOf(
                    "Can explain the N+1 SELECT problem and fix it using `@EntityGraph` or `JOIN FETCH`",
                    "Can run database migrations using Flyway without wiping database state"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m8_t1", "Connect Spring Boot app to local PostgreSQL database"),
                    RoadmapTaskModel("m8_t2", "Model relational entities with @ManyToOne and @OneToMany using FetchType.LAZY"),
                    RoadmapTaskModel("m8_t3", "Fix N+1 query issue using JPQL JOIN FETCH in Spring Data JPA"),
                    RoadmapTaskModel("m8_t4", "Add Flyway database migration scripts to Spring Boot project")
                )
            ),
            MonthlyStageModel(
                month = 9,
                quarter = "Q3",
                year = 1,
                title = "Authentication, Authorization & Security (JWT, Spring Security, BCrypt)",
                focusArea = "API Security & Identity Management",
                whatToLearn = listOf(
                    "Authentication vs Authorization principles",
                    "Password Hashing: Salt + BCrypt algorithm",
                    "JSON Web Tokens (JWT): Header, Payload, Signature, Access Token vs Refresh Token architecture",
                    "Spring Security 6 Configuration: SecurityFilterChain, OncePerRequestFilter, Stateless session management"
                ),
                whyNeeded = "Every production backend requires secure authentication. Employers reject projects that use hardcoded tokens or plain passwords.",
                depthLevel = "Security implementation level — write secure filter chains, handle token expiration, and prevent OWASP Top 10 vulnerabilities.",
                prerequisites = "Spring Boot Core & JPA Persistence.",
                recommendedOrder = listOf("1. BCrypt Hashing", "2. JWT Generation & Verification", "3. Spring Security Filter Chain", "4. Refresh Token Flow"),
                whatNotToLearnYet = listOf("OAuth2 / OpenID Connect complex federation (unless needed later)"),
                practiceRequirements = "Implement full auth flow (User Register, Login, Refresh Token, Access Control) in Spring Security.",
                projectRequirements = "Project 2 (Portfolio Starter): Secure Multi-Tenant Backend API with JWT Auth, Role-Based Access Control (RBAC), and Rate Limiting.",
                readinessCheck = listOf(
                    "Can explain how JWT signature verification works without database lookup",
                    "Can secure endpoints based on user roles (ROLE_USER, ROLE_ADMIN)",
                    "Milestone 3 Self-Audit (M9 Scorecard Target: >= 80/100)"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m9_t1", "Implement User Registration with BCrypt password hashing"),
                    RoadmapTaskModel("m9_t2", "Create JWT Utility class for signing and validating tokens"),
                    RoadmapTaskModel("m9_t3", "Configure Spring Security 6 Stateless SecurityFilterChain"),
                    RoadmapTaskModel("m9_t4", "Build Refresh Token rotation mechanism and test protected routes")
                )
            ),
            MonthlyStageModel(
                month = 10,
                quarter = "Q4",
                year = 1,
                title = "Operating Systems & Computer Networks for Interviews",
                focusArea = "Core CS Technical Interview Mastery",
                whatToLearn = listOf(
                    "Operating Systems: Process vs Thread, CPU Scheduling (FCFS, Round Robin), Process Synchronization (Mutex, Semaphore, Deadlock conditions)",
                    "OS Memory Management: Paging, Virtual Memory, Page Faults, Thrashing",
                    "Computer Networks: OSI 7 Layers vs TCP/IP 4 Layers, TCP 3-Way Handshake, UDP vs TCP",
                    "DNS Lookup Process, HTTP vs HTTPS (TLS/SSL Handshake), Cookies vs LocalStorage vs Session"
                ),
                whyNeeded = "OS and Computer Networks are tested heavily in 2nd round technical interviews at product companies (Amazon, Cisco, Samsung, Microsoft, Startups).",
                depthLevel = "Interview articulate level — explain deadlock prevention, TCP vs UDP tradeoffs, and DNS lookups with clarity.",
                prerequisites = "Basic C programming & system mental model.",
                recommendedOrder = listOf("1. Process vs Thread & CPU Scheduling", "2. Mutex & Deadlocks", "3. Virtual Memory & Paging", "4. TCP/IP & Handshake", "5. HTTP/HTTPS"),
                whatNotToLearnYet = listOf("Low-level Kernel Driver Development", "BGP Routing Protocols"),
                practiceRequirements = "Answer 40 core OS/CN interview question flashcards out loud.",
                projectRequirements = "Write a Multithreaded Web Server in Java/C++ or simulate a Producer-Consumer buffer using Semaphores.",
                readinessCheck = listOf(
                    "Can explain 4 necessary conditions for Deadlock and how to prevent them",
                    "Can explain step-by-step what happens when you type 'https://google.com' in browser bar"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m10_t1", "Study Process States, Context Switching, and Multithreading"),
                    RoadmapTaskModel("m10_t2", "Master Mutex, Semaphore, and Producer-Consumer synchronization problem"),
                    RoadmapTaskModel("m10_t3", "Study OSI Model, TCP 3-Way Handshake, and TLS Handshake"),
                    RoadmapTaskModel("m10_t4", "Build Multithreaded HTTP Server in Java socket programming")
                )
            ),
            MonthlyStageModel(
                month = 11,
                quarter = "Q4",
                year = 1,
                title = "Caching (Redis) & Containerization (Docker)",
                focusArea = "High-Performance Backend Infrastructure",
                whatToLearn = listOf(
                    "Caching Fundamentals: Cache-Aside, Write-Through, Cache Invalidation, TTL (Time To Live)",
                    "Redis: Data structures (Strings, Hashes, Lists, Sets, Sorted Sets), Spring Cache integration (@Cacheable, @CacheEvict)",
                    "Docker Basics: Container vs Virtual Machine, Dockerfile instruction set, Image building",
                    "Docker Compose: Multi-container setup (Spring Boot App + PostgreSQL + Redis)"
                ),
                whyNeeded = "Modern production backends use Redis for sub-millisecond cache lookups and Docker to ensure 'works on my machine' works everywhere.",
                depthLevel = "Hands-on DevOps/Backend level — containerize multi-service app with Docker Compose.",
                prerequisites = "Spring Boot REST API & PostgreSQL setup.",
                recommendedOrder = listOf("1. Redis Caching in Spring Boot", "2. Cache Invalidation strategies", "3. Dockerfile syntax", "4. Docker Compose setup"),
                whatNotToLearnYet = listOf("Kubernetes (K8s) Cluster Administration", "Service Mesh (Istio)"),
                practiceRequirements = "Cache high-traffic API responses in Redis and measure latency improvement (e.g., from 150ms to 8ms).",
                projectRequirements = "Containerize Project 2 backend + PostgreSQL + Redis using Docker Compose.",
                readinessCheck = listOf(
                    "Can run `docker-compose up` to launch entire stack cleanly",
                    "Can explain Cache Stampede and Cache Penetration with mitigation strategies"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m11_t1", "Install Redis and test commands in redis-cli"),
                    RoadmapTaskModel("m11_t2", "Integrate Redis caching into Spring Boot using @Cacheable"),
                    RoadmapTaskModel("m11_t3", "Write optimized multi-stage Dockerfile for Spring Boot executable JAR"),
                    RoadmapTaskModel("m11_t4", "Create docker-compose.yml orchestrating App, PostgreSQL, and Redis")
                )
            ),
            MonthlyStageModel(
                month = 12,
                quarter = "Q4",
                year = 1,
                title = "Project 2 Completion, Portfolio Website & Resume 1.0",
                focusArea = "Year 1 Portfolio Launch & Internship Prep",
                whatToLearn = listOf(
                    "Resume Engineering: Single-page ATS-friendly LaTeX/Overleaf template (Deedy/Jake's Resume style)",
                    "GitHub Profile Polish: README profiling, pin projects, clear documentation with architecture diagrams",
                    "LinkedIn Profile Optimization: Professional headline, detailed project bullet points, tech keywords",
                    "Open Source Contribution Basics: Forking, fixing open issue, sending pull request"
                ),
                whyNeeded = "B.Sc CS students MUST present a polished, high-signal resume and GitHub profile to pass initial recruiter screenings.",
                depthLevel = "High recruiter impact — bullet points formatted as 'Action Verb + Technical Challenge + Solution + Quantifiable Impact'.",
                prerequisites = "Projects 1 & 2 complete and deployed/containerized.",
                recommendedOrder = listOf("1. Resume Drafting", "2. GitHub READMEs", "3. Portfolio Website", "4. LinkedIn Polish"),
                whatNotToLearnYet = listOf("Mass cold emailing before resume is verified"),
                practiceRequirements = "Build clean developer portfolio website (using GitHub Pages/Vercel) hosting project demos and architecture charts.",
                projectRequirements = "Complete Project 2 with complete OpenAPI Swagger docs, Postman collection, and live Docker demo video/GIF.",
                readinessCheck = listOf(
                    "ATS Resume passes parser with > 80% keyword match for SDE Intern roles",
                    "GitHub profile has green contribution graph and pinned repositories with architecture diagrams",
                    "Milestone 4 Self-Audit (M12 Scorecard Target: >= 82/100)"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m12_t1", "Build ATS Resume in Overleaf LaTeX template"),
                    RoadmapTaskModel("m12_t2", "Create detailed GitHub README.md for Project 1 & Project 2 with diagrams"),
                    RoadmapTaskModel("m12_t3", "Deploy Portfolio Website showcasing projects and live API Swagger links"),
                    RoadmapTaskModel("m12_t4", "Execute Internship Preparation Checklist & start networking on LinkedIn")
                )
            ),
            MonthlyStageModel(
                month = 13,
                quarter = "Q5",
                year = 2,
                title = "Internship Hunt & Advanced DSA (Hard Graph & DP Patterns)",
                focusArea = "Active Internship Applications & Advanced DSA",
                whatToLearn = listOf(
                    "Advanced DP Patterns: DP on Trees, DP with Bitmask, Longest Common Subsequence (LCS), Edit Distance",
                    "Advanced Graphs: Strongly Connected Components (Tarjan / Kosaraju), Minimum Spanning Tree (Kruskal / Prim)",
                    "Internship Cold Outreach Strategy: Targeting tech founders, engineering managers on LinkedIn/Twitter/Email",
                    "Mock Technical Interviews: Peer mock interviews on Pramp / Interviewing.io"
                ),
                whyNeeded = "Securing a 3-6 month software engineering internship during Year 2 gives real production experience that eliminates B.Sc degree bias.",
                depthLevel = "High problem-solving speed — solve Medium LeetCode problem in 20 minutes under interview pressure.",
                prerequisites = "Month 1-6 DSA foundations & Month 12 Resume.",
                recommendedOrder = listOf("1. Cold outreach & applications (15-20 per week)", "2. Mock DSA interviews", "3. Advanced DP", "4. Advanced Graphs"),
                whatNotToLearnYet = listOf("Over-engineered Microservice Service Meshes"),
                practiceRequirements = "Submit 20 tailored applications/cold-emails per week. Solve 20 Advanced DSA problems.",
                projectRequirements = "Maintain live application tracking spreadsheet with follow-up reminders.",
                readinessCheck = listOf(
                    "Consistently getting past initial resume screens or getting OA links",
                    "Can solve LeetCode Medium problems in under 25 minutes"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m13_t1", "Send 20 personalized cold emails / LinkedIn messages to EMs and founders"),
                    RoadmapTaskModel("m13_t2", "Practice 2 timed Online Assessments (OA) per week on LeetCode/Code360"),
                    RoadmapTaskModel("m13_t3", "Solve 10 Advanced DP problems (LCS, Edit Distance, Coin Change II)"),
                    RoadmapTaskModel("m13_t4", "Conduct 3 peer mock technical interviews")
                )
            ),
            MonthlyStageModel(
                month = 14,
                quarter = "Q5",
                year = 2,
                title = "System Design Foundations (Scalability, Load Balancing, DB Sharding)",
                focusArea = "High-Level & Low-Level System Design",
                whatToLearn = listOf(
                    "Scalability Concepts: Vertical vs Horizontal Scaling, Stateless vs Stateful servers",
                    "Load Balancers: Round Robin, Least Connections, Consistent Hashing",
                    "Database Scaling: Read Replicas, Primary-Secondary Replication, Database Sharding/Partitioning",
                    "Message Queues & Asynchronous Processing: Producer-Consumer pattern, Apache Kafka / RabbitMQ basics"
                ),
                whyNeeded = "System Design separates entry-level coders from high-paying SDEs (₹12-20+ LPA rounds require LLD/HLD fundamentals).",
                depthLevel = "Architectural reasoning level — evaluate tradeoffs (e.g. Consistency vs Availability in CAP Theorem).",
                prerequisites = "Backend API experience, SQL, Redis, Docker.",
                recommendedOrder = listOf("1. Monolith vs Microservices", "2. Load Balancing & Caching", "3. DB Replication & Sharding", "4. Message Queues"),
                whatNotToLearnYet = listOf("Deep Distributed Consensus Protocols (Raft/Paxos implementation details)"),
                practiceRequirements = "Draw architecture diagrams for URL Shortener, Rate Limiter, and Notification System.",
                projectRequirements = "Project 3 (Production Backend): High-Throughput Distributed Rate Limiter & URL Analytics Service using Redis & Spring Boot.",
                readinessCheck = listOf(
                    "Can design a URL Shortener (TinyURL) handling 10,000 requests/sec with custom hashing & Redis cache",
                    "Can explain CAP Theorem with real database examples (PostgreSQL vs Cassandra)"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m14_t1", "Master CAP Theorem, Consistent Hashing, and Load Balancing algorithms"),
                    RoadmapTaskModel("m14_t2", "Design High-Level Architecture for TinyURL and Distributed Rate Limiter"),
                    RoadmapTaskModel("m14_t3", "Implement Spring Boot + Redis Distributed Rate Limiter filter"),
                    RoadmapTaskModel("m14_t4", "Document Project 3 system design with architecture diagrams on GitHub")
                )
            ),
            MonthlyStageModel(
                month = 15,
                quarter = "Q5",
                year = 2,
                title = "Project 3 (High-Throughput Backend) & Asynchronous Messaging",
                focusArea = "Event-Driven Architecture & Message Queues",
                whatToLearn = listOf(
                    "Event-Driven Architecture: Synchronous REST vs Asynchronous Messaging",
                    "RabbitMQ / Apache Kafka: Topics, Queues, Producers, Consumers, Consumer Groups",
                    "Idempotency in Distributed Systems: Unique transaction keys, handling duplicate messages",
                    "Dead Letter Queues (DLQ) & Retry Policies"
                ),
                whyNeeded = "High-scale Indian tech platforms (Swiggy, Flipkart, PhonePe) rely heavily on asynchronous event streaming for order & payment processing.",
                depthLevel = "Implementation level — build resilient message consumer handling retries and DLQ.",
                prerequisites = "Spring Boot, Docker, System Design fundamentals.",
                recommendedOrder = listOf("1. Sync vs Async tradeoffs", "2. RabbitMQ/Kafka setup in Docker", "3. Producer & Consumer implementation", "4. Error handling & DLQ"),
                whatNotToLearnYet = listOf("Complex Multi-Region Kafka Mirroring"),
                practiceRequirements = "Send 1,000 asynchronous event tasks to queue and process with worker pool.",
                projectRequirements = "Add Asynchronous Email/SMS Notification Engine to Project 3 using RabbitMQ/Kafka.",
                readinessCheck = listOf(
                    "Can explain why message queues prevent server crashes during traffic spikes",
                    "Can implement idempotent event processing in Spring Boot"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m15_t1", "Set up RabbitMQ/Kafka service in Docker Compose"),
                    RoadmapTaskModel("m15_t2", "Implement Event Producer in Spring Boot service"),
                    RoadmapTaskModel("m15_t3", "Implement Resilient Event Consumer with Retry and Dead Letter Queue"),
                    RoadmapTaskModel("m15_t4", "Milestone 5 Self-Audit (M15 Scorecard Target: >= 85/100)")
                )
            ),
            MonthlyStageModel(
                month = 16,
                quarter = "Q6",
                year = 2,
                title = "Low-Level Design (LLD), Object-Oriented Design & Design Patterns",
                focusArea = "Clean Architecture & Design Patterns",
                whatToLearn = listOf(
                    "SOLID Principles: Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion",
                    "Creational Design Patterns: Singleton, Factory, Builder, Prototype",
                    "Structural Design Patterns: Adapter, Decorator, Proxy, Facade",
                    "Behavioral Design Patterns: Strategy, Observer, Command, State",
                    "Machine Coding Round Preparation: Designing Tic-Tac-Toe, Parking Lot, Elevator System, Snake & Ladders"
                ),
                whyNeeded = "Machine Coding and LLD rounds are standard at companies like Swiggy, Flipkart, Uber, Razorpay, and CRED.",
                depthLevel = "Clean Code & Extensibility level — write code that can easily accommodate new requirements without modifying existing classes.",
                prerequisites = "Java OOP mastery.",
                recommendedOrder = listOf("1. SOLID Principles", "2. Behavioral Patterns", "3. Creational Patterns", "4. Machine Coding practice"),
                whatNotToLearnYet = listOf("Domain-Driven Design (DDD) enterprise hyper-abstractions"),
                practiceRequirements = "Solve 4 classic Machine Coding problems in 90 minutes each with working unit tests.",
                projectRequirements = "Build clean extensible LLD for Parking Lot System in Java with modular pricing strategies.",
                readinessCheck = listOf(
                    "Can implement Strategy Pattern for dynamic pricing engine in 20 minutes",
                    "Can explain all 5 SOLID principles with real Java code examples"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m16_t1", "Write code examples illustrating all 5 SOLID Principles"),
                    RoadmapTaskModel("m16_t2", "Implement Factory, Builder, Observer, and Strategy Patterns"),
                    RoadmapTaskModel("m16_t3", "Complete Machine Coding Problem 1: Parking Lot System in Java"),
                    RoadmapTaskModel("m16_t4", "Complete Machine Coding Problem 2: Elevator Control System in Java")
                )
            ),
            MonthlyStageModel(
                month = 17,
                quarter = "Q6",
                year = 2,
                title = "Cloud Fundamentals (AWS/GCP), CI/CD & Deployment",
                focusArea = "Production Deployment & Cloud Infrastructure",
                whatToLearn = listOf(
                    "Cloud Basics (AWS / GCP / Render): EC2 / Compute Engine, S3 / Cloud Storage, RDS / Managed PostgreSQL",
                    "CI/CD Pipelines: GitHub Actions workflows (.github/workflows/main.yml)",
                    "Automated Testing in Pipeline: Running JUnit 5 & Integration tests before build",
                    "Application Monitoring & Logging: SLF4J + Logback, Prometheus & Grafana basic metrics"
                ),
                whyNeeded = "Having live deployed projects with automated CI/CD pipelines makes your resume stand out dramatically over candidates with local-only projects.",
                depthLevel = "DevOps operational level — automatic deployment to Cloud on git push.",
                prerequisites = "Docker, Git, Spring Boot.",
                recommendedOrder = listOf("1. GitHub Actions CI pipeline", "2. Cloud VM setup (AWS EC2 / Render)", "3. S3 file storage integration", "4. Live deployment"),
                whatNotToLearnYet = listOf("Terraform / Infrastructure-as-Code (unless interested in DevOps)"),
                practiceRequirements = "Configure GitHub Actions to automatically run tests, build Docker image, and deploy to Cloud VM on main branch push.",
                projectRequirements = "Deploy Project 3 to AWS EC2 / Render with live domain, SSL certificate, and GitHub Actions CI/CD.",
                readinessCheck = listOf(
                    "Live URL accessible over HTTPS with Swagger UI working",
                    "GitHub Actions pipeline turns green on every commit"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m17_t1", "Write GitHub Actions workflow for automated testing and Docker build"),
                    RoadmapTaskModel("m17_t2", "Provision free-tier AWS EC2 instance / Render service"),
                    RoadmapTaskModel("m17_t3", "Integrate AWS S3 / Cloudinary for cloud file uploads in backend"),
                    RoadmapTaskModel("m17_t4", "Deploy live production backend with custom domain & SSL")
                )
            ),
            MonthlyStageModel(
                month = 18,
                quarter = "Q6",
                year = 2,
                title = "Project 4 (Flagship Real-World SaaS) & Full-Stack Synergy",
                focusArea = "Flagship Production System Build",
                whatToLearn = listOf(
                    "Project 4 (Flagship): Microservice / Event-Driven Real-Time Platform (e.g., Real-Time Collaborative Workspace or Scalable E-Commerce Backend)",
                    "API Gateway & Service Security: Rate Limiting, Route Forwarding, Centralized Auth",
                    "WebSockets / Server-Sent Events (SSE) for real-time bidirectional updates",
                    "Performance Profiling & Database Query Tuning"
                ),
                whyNeeded = "This flagship project will be the focal point of your senior technical interview rounds, demonstrating production readiness.",
                depthLevel = "Enterprise production grade — complete logging, security, caching, live deployment, load testing report.",
                prerequisites = "All previous backend, database, system design, and cloud topics.",
                recommendedOrder = listOf("1. Architecture design", "2. Core microservices", "3. WebSockets/SSE", "4. Load testing with Apache JMeter / k6"),
                whatNotToLearnYet = listOf("Unnecessary front-end heavy frameworks (keep simple UI or API docs)"),
                practiceRequirements = "Run load testing tool (k6 / JMeter) simulating 500 concurrent users and optimize bottlenecks.",
                projectRequirements = "Complete Project 4 with full documentation, k6 benchmark report, live link, and GitHub repository.",
                readinessCheck = listOf(
                    "Can defend every architectural decision in Project 4 during an interview",
                    " k6 load test proves backend handles 1000+ requests/minute with < 50ms latency",
                    "Milestone 6 Self-Audit (M18 Scorecard Target: >= 88/100)"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m18_t1", "Architect Project 4 Flagship System (Real-Time Platform)"),
                    RoadmapTaskModel("m18_t2", "Implement WebSocket server for real-time state synchronization"),
                    RoadmapTaskModel("m18_t3", "Conduct k6 / JMeter load testing and optimize database indexes"),
                    RoadmapTaskModel("m18_t4", "Publish comprehensive Project 4 Case Study & Architecture Blueprint on GitHub")
                )
            ),
            MonthlyStageModel(
                month = 19,
                quarter = "Q7",
                year = 2,
                title = "AI-Enabled Software Engineering & Tool Integration",
                focusArea = "AI Productivity & Modern Developer Workflow",
                whatToLearn = listOf(
                    "AI Coding Tools Mastery: GitHub Copilot, Cursor AI, Claude, Gemini Code Assist for fast scaffolding & refactoring",
                    "AI API Integration in Spring Boot: Integrating LangChain4j / Spring AI / Gemini REST API for LLM features",
                    "Vector Databases & RAG Basics: PGVector in PostgreSQL, embedding generation, Semantic Search",
                    "Code Review & Security Auditing with AI: AI-assisted test generation without code hallucination"
                ),
                whyNeeded = "Top companies hire 'AI-Enabled Engineers' who write high-quality code 3x faster using AI tools while retaining deep core fundamentals.",
                depthLevel = "Integration & Productivity level — integrate Gemini API / RAG features into Project 4.",
                prerequisites = "Spring Boot, REST APIs, PostgreSQL.",
                recommendedOrder = listOf("1. Spring AI / Gemini API setup", "2. Vector Search with PGVector", "3. AI Copilot workflows", "4. AI Code Auditing"),
                whatNotToLearnYet = listOf("Training LLMs from scratch or PyTorch CUDA C++ kernels"),
                practiceRequirements = "Add AI-powered Semantic Search or Document Summarizer feature to Project 4.",
                projectRequirements = "Enhance Project 4 with AI Assistant API endpoint using Gemini API & Spring AI.",
                readinessCheck = listOf(
                    "Can explain how Vector Embeddings and RAG (Retrieval-Augmented Generation) work",
                    "Can write unit tests 2x faster using AI tools while verifying logic manually"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m19_t1", "Integrate Gemini API / Spring AI into Spring Boot backend"),
                    RoadmapTaskModel("m19_t2", "Enable PGVector extension in PostgreSQL for vector embeddings"),
                    RoadmapTaskModel("m19_t3", "Build Semantic Search endpoint using embeddings"),
                    RoadmapTaskModel("m19_t4", "Master Cursor/Copilot prompt engineering for unit test generation")
                )
            ),
            MonthlyStageModel(
                month = 20,
                quarter = "Q7",
                year = 2,
                title = "Mass Off-Campus Application Sprint & Referral Strategy",
                focusArea = "High-Volume Off-Campus Application Engine",
                whatToLearn = listOf(
                    "Indian Off-Campus Job Portals: Wellfound (AngelList), Instahyre, Hirist, LinkedIn Jobs, Naukri.com, Cutshort",
                    "Referral Hacking Strategy: Finding alumni and engineers on LinkedIn, personalized referral requests",
                    "Cold Emailing Playbook: Direct outreach to CTOs, VPs of Engineering at Series A/B/C startups",
                    "B.Sc Degree Workaround Tactics: Highlighting live project URLs, open source, and hackathon wins to bypass degree filters"
                ),
                whyNeeded = "Off-campus hiring requires active application pipelines rather than passive waiting.",
                depthLevel = "Conversion level — convert 50 applications into 3-5 interview callbacks per week.",
                prerequisites = "All 4 Projects live, Resume 2.0, GitHub polished, 250+ DSA problems solved.",
                recommendedOrder = listOf("1. Instahyre & Wellfound setup", "2. Cold Email Campaign", "3. LinkedIn Referral requests", "4. Company tracking"),
                whatNotToLearnYet = listOf("Random un-targeted course watching"),
                practiceRequirements = "Send 30-40 targeted applications per week with 10 personalized cold outreach messages.",
                projectRequirements = "Maintain active Application Tracker with status, follow-up dates, and recruiter contact info.",
                readinessCheck = listOf(
                    "50+ total applications submitted across Instahyre, Wellfound, and LinkedIn",
                    "At least 2-3 interview/OA calls scheduled per week"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m20_t1", "Optimize profiles on Instahyre, Wellfound, Hirist, and Naukri"),
                    RoadmapTaskModel("m20_t2", "Send 15 cold emails daily to Tech Founders and Engineering Leads"),
                    RoadmapTaskModel("m20_t3", "Request 10 LinkedIn referrals per week with customized message template"),
                    RoadmapTaskModel("m20_t4", "Track all application responses and follow up after 4 days")
                )
            ),
            MonthlyStageModel(
                month = 21,
                quarter = "Q7",
                year = 2,
                title = "Online Assessment (OA) Speed Drills & Technical Screenings",
                focusArea = "Coding Speed & Test-Taking Precision",
                whatToLearn = listOf(
                    "HackerRank / CodeSignal / Mettl OA Environment Mastery: Fast I/O, custom test cases, edge case debugging",
                    "Time Management in Coding Tests: 15 mins for Easy, 30 mins for Medium, knowing when to move on",
                    "Common OA Trick Questions: Bit manipulation tricks, sliding window constraints, custom comparator sorting",
                    "Debugging under time pressure: Using print statements and dry runs effectively"
                ),
                whyNeeded = "80% of candidates fail the Online Assessment (OA) round due to speed issues or hidden edge cases.",
                depthLevel = "Test-taking efficiency — score 100% test case pass rate in under 45 minutes.",
                prerequisites = "250+ DSA problems solved.",
                recommendedOrder = listOf("1. Timed 60-min contests", "2. Fast I/O & template setup", "3. Edge case checklists", "4. Past company OA problems"),
                whatNotToLearnYet = listOf("Learning brand new complex data structures from scratch"),
                practiceRequirements = "Take 3 timed LeetCode / Codeforces contests per week.",
                projectRequirements = "Build personal reusable DSA Code Template library.",
                readinessCheck = listOf(
                    "Passing 80%+ of test cases in timed online coding assessments",
                    "Zero syntax errors or compile bugs during timed tests"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m21_t1", "Participate in 4 LeetCode Weekly Contests under strict timing"),
                    RoadmapTaskModel("m21_t2", "Solve 20 past OA problems from Amazon, Swiggy, and Flipkart"),
                    RoadmapTaskModel("m21_t3", "Refine Fast I/O and custom comparator templates in Java/C++"),
                    RoadmapTaskModel("m21_t4", "Audit common edge cases (int overflow, empty arrays, single element)")
                )
            ),
            MonthlyStageModel(
                month = 22,
                quarter = "Q8",
                year = 2,
                title = "Live Technical & System Design Interview Simulation",
                focusArea = "Interview Communication & Behavioral Delivery",
                whatToLearn = listOf(
                    "Thinking Out Loud Protocol: Communicating approach before writing a single line of code",
                    "Clarifying Questions Strategy: Asking about constraints, inputs, null handling before coding",
                    "Project Defense Mastery: Explaining architectural choices, database trade-offs, and scaling bottlenecks in Projects 1-4",
                    "Behavioral Interviewing (STAR Method): Situation, Task, Action, Result for leadership, conflict, and challenge questions"
                ),
                whyNeeded = "In live interviews, interviewers evaluate thought process, communication, and receptiveness to feedback as much as raw code.",
                depthLevel = "Senior engineer communication level — clear, structured, confident, and receptive.",
                prerequisites = "All technical modules complete.",
                recommendedOrder = listOf("1. STAR method stories", "2. Project defense prep", "3. Pramp mock interviews", "4. Feedback iteration"),
                whatNotToLearnYet = listOf("Cramming new theoretical concepts"),
                practiceRequirements = "Complete 5 full peer/mentor mock interviews (DSA + System Design + HR).",
                projectRequirements = "Create Project Defense Cheat Sheet summarizing architectural decisions, DB schemas, and metrics.",
                readinessCheck = listOf(
                    "Can explain any project architecture in 3 minutes without stuttering",
                    "Receiving positive feedback on communication and approach in mock interviews"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m22_t1", "Conduct 3 live DSA mock interviews on Pramp / peer network"),
                    RoadmapTaskModel("m22_t2", "Conduct 2 System Design / Backend project mock interviews"),
                    RoadmapTaskModel("m22_t3", "Prepare 5 STAR method stories for HR/Behavioral rounds"),
                    RoadmapTaskModel("m22_t4", "Refine project presentation slides / live demo URLs")
                )
            ),
            MonthlyStageModel(
                month = 23,
                quarter = "Q8",
                year = 2,
                title = "Active Interview Execution & Salary Negotiation",
                focusArea = "Job Offer Conversions & Negotiation",
                whatToLearn = listOf(
                    "Offer Negotiation Dynamics for Freshers: Leveraging competing offers, base salary vs CTC breakups",
                    "Evaluating Job Offers: Health insurance, ESOPs vs Cash, remote flexibility, growth potential",
                    "Notice Period & Joining Protocol: Clear communication with HRs, background checks, document readiness",
                    "Handling Rejections Productively: Post-interview retro, logging missed questions, continuous iteration"
                ),
                whyNeeded = "Understanding compensation structure prevents signing low-ball or exploitative contracts.",
                depthLevel = "Professional negotiation level — secure maximum base salary and fair growth terms.",
                prerequisites = "Active interview calls in progress.",
                recommendedOrder = listOf("1. Interview execution", "2. Offer evaluation", "3. CTC vs Base analysis", "4. Negotiation"),
                whatNotToLearnYet = listOf("Stopping practice while waiting for single offer result"),
                practiceRequirements = "Maintain interview pipeline until written offer letter is signed.",
                projectRequirements = "Keep coding daily (at least 1 problem/day) to stay sharp.",
                readinessCheck = listOf(
                    "At least 1 written job offer in hand with base salary >= target",
                    "Clear understanding of CTC vs In-hand breakdown"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m23_t1", "Attend live technical & HR rounds with confidence"),
                    RoadmapTaskModel("m23_t2", "Evaluate job offers using CTC vs In-hand Calculator"),
                    RoadmapTaskModel("m23_t3", "Negotiate base salary respectfully using competing offer leverage"),
                    RoadmapTaskModel("m23_t4", "Finalize employment offer & complete background verification prep")
                )
            ),
            MonthlyStageModel(
                month = 24,
                quarter = "Q8",
                year = 2,
                title = "Job Onboarding, Final Graduation Audit & Early Career Roadmap",
                focusArea = "Transition to High-Paying SDE Role",
                whatToLearn = listOf(
                    "First 90 Days SDE Playbook: Understanding company codebase, writing documentation, making early impactful PRs",
                    "Continuous Career Progression: Moving from SDE-1 to SDE-2 in 18-24 months",
                    "Building Professional Brand: Writing technical blogs on Medium/Dev.to, open-source maintainership",
                    "Final 24-Month Master Roadmap Audit"
                ),
                whyNeeded = "Completing the 24-month journey successfully transitions you into a high-earning software engineer.",
                depthLevel = "Production software engineer level — ready to write production code on Day 1.",
                prerequisites = "Job offer secured.",
                recommendedOrder = listOf("1. Graduation Audit", "2. Codebase onboarding prep", "3. Professional branding", "4. Long-term career goal setting"),
                whatNotToLearnYet = listOf("Complacency"),
                practiceRequirements = "Review internal codebase architecture and Git workflows.",
                projectRequirements = "Publish final retrospectives and open-source portfolio.",
                readinessCheck = listOf(
                    "Graduated from B.Sc CS as a fully qualified, high-value Software Engineer",
                    "Joined target tech company with ₹10 - 20+ LPA pathway unlocked!",
                    "Milestone 7 Final Audit (M24 Target: 100/100 Mastered)"
                ),
                tasks = listOf(
                    RoadmapTaskModel("m24_t1", "Complete Final 24-Month Roadmap Checklist"),
                    RoadmapTaskModel("m24_t2", "Publish 24-Month Learning Journey & Project Showcase on LinkedIn"),
                    RoadmapTaskModel("m24_t3", "Prepare workspace & setup Linux/Mac environment for SDE job"),
                    RoadmapTaskModel("m24_t4", "CELEBRATE SUCCESS: You are officially a High-Earning Software Engineer!")
                )
            )
        )
    }

    // --- 3. PROGRAMMING LANGUAGE STRATEGY ---
    fun getLanguageStrategy(): Map<String, Any> {
        return mapOf(
            "summary" to "Minimum language strategy: Learn C++ (or Java) for DSA & Interviews + Java for Enterprise Backend Development + Python for AI scripts.",
            "dsaLanguageChoice" to mapOf(
                "winner" to "C++ (or Java)",
                "cppPros" to listOf("Blazing fast execution in OAs", "Standard Template Library (STL) vector/map/set/priority_queue syntax is concise", "Most popular choice in Indian college coding competitive culture"),
                "javaPros" to listOf("Massive enterprise demand in India", "Clean Object-Oriented syntax", "Built-in Garbage Collection prevents raw pointer memory bugs", "Direct alignment with Spring Boot backend framework"),
                "recommendation" to "If you want pure competitive coding speed: Pick C++. If you want 100% alignment with your backend stack (Java + Spring Boot): Pick Java. Both are top-tier choices for Indian interviews. AVOID Python for DSA in Indian OAs because time limits are strict and Python sometimes gets TLE (Time Limit Exceeded)."
            ),
            "backendLanguage" to "Java 21+ with Spring Boot 3 (Highest job volume in India)",
            "automationAiLanguage" to "Python (Use strictly for scripting, data processing, and AI API calls - do not use for primary backend or DSA)",
            "postponedLanguages" to listOf("Rust", "Go (Golang - learn after 2 yrs SDE experience)", "PHP", "Ruby", "C# / .NET")
        )
    }

    // --- 4. PATTERN-BASED DSA CATALOG (250-350 PROBLEM STRATEGY) ---
    fun getDsaPatterns(): List<DsaPatternModel> {
        return listOf(
            DsaPatternModel(
                id = "pattern_arrays_2pointers",
                category = "Arrays & Strings",
                title = "Two Pointers (Opposite & Fast/Slow)",
                coreConcept = "Maintain two pointers traversing array from opposite ends or at different speeds to solve search/pair problems in O(N) time and O(1) space.",
                keyProblemsCount = 20,
                cppTemplate = """// C++ Two Pointers Template
int left = 0, right = nums.size() - 1;
while (left < right) {
    int currentSum = nums[left] + nums[right];
    if (currentSum == target) return {left, right};
    else if (currentSum < target) left++;
    else right--;
}""",
                javaTemplate = """// Java Two Pointers Template
int left = 0, right = nums.length - 1;
while (left < right) {
    int currentSum = nums[left] + nums[right];
    if (currentSum == target) return new int[]{left, right};
    else if (currentSum < target) left++;
    else right--;
}""",
                commonMistakes = listOf("Forgetting that array must be sorted for two-sum opposite pointers", "Off-by-one errors in loop condition (left < right vs left <= right)"),
                interviewWeight = "Critical (Asked in 90% OAs)"
            ),
            DsaPatternModel(
                id = "pattern_sliding_window",
                category = "Arrays & Strings",
                title = "Sliding Window (Fixed & Dynamic)",
                coreConcept = "Maintain a contiguous subarray/substring window that expands and shrinks to track optimal subarray metrics in O(N) time.",
                keyProblemsCount = 25,
                cppTemplate = """// C++ Variable Sliding Window Template
int left = 0, maxLen = 0;
unordered_map<char, int> freq;
for (int right = 0; right < s.length(); right++) {
    freq[s[right]]++;
    while (/* condition invalid */) {
        freq[s[left]]--;
        if (freq[s[left]] == 0) freq.erase(s[left]);
        left++;
    }
    maxLen = max(maxLen, right - left + 1);
}""",
                javaTemplate = """// Java Variable Sliding Window Template
int left = 0, maxLen = 0;
Map<Character, Integer> freq = new HashMap<>();
for (int right = 0; right < s.length(); right++) {
    char c = s.charAt(right);
    freq.put(c, freq.getOrDefault(c, 0) + 1);
    while (/* condition invalid */) {
        char leftChar = s.charAt(left);
        freq.put(leftChar, freq.get(leftChar) - 1);
        if (freq.get(leftChar) == 0) freq.remove(leftChar);
        left++;
    }
    maxLen = Math.max(maxLen, right - left + 1);
}""",
                commonMistakes = listOf("Shrinking window with 'if' instead of 'while' loop", "Not updating map counts when moving left boundary"),
                interviewWeight = "Critical"
            ),
            DsaPatternModel(
                id = "pattern_monotonic_stack",
                category = "Stacks & Queues",
                title = "Monotonic Stack (Next Greater Element)",
                coreConcept = "Maintain a stack with elements strictly increasing or decreasing to find nearest smaller or greater elements in O(N).",
                keyProblemsCount = 15,
                cppTemplate = """// C++ Monotonic Decreasing Stack Template
stack<int> st;
vector<int> result(n, -1);
for (int i = 0; i < n; i++) {
    while (!st.empty() && nums[st.top()] < nums[i]) {
        result[st.top()] = nums[i];
        st.pop();
    }
    st.push(i);
}""",
                javaTemplate = """// Java Monotonic Decreasing Stack Template
Stack<Integer> st = new Stack<>();
int[] result = new int[n];
Arrays.fill(result, -1);
for (int i = 0; i < n; i++) {
    while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
        result[st.peek()] = nums[i];
        st.pop();
    }
    st.push(i);
}""",
                commonMistakes = listOf("Storing values instead of element indices in stack", "Reversing greater/smaller comparison operator"),
                interviewWeight = "High"
            ),
            DsaPatternModel(
                id = "pattern_fast_slow_ll",
                category = "Linked Lists",
                title = "Fast & Slow Pointers (Floyd's Cycle)",
                coreConcept = "Use two pointers moving at speed 1x and 2x to detect cycles or find middle node of Linked List in one pass.",
                keyProblemsCount = 12,
                cppTemplate = """// C++ Fast & Slow Pointer Template
ListNode *slow = head, *fast = head;
while (fast != nullptr && fast->next != nullptr) {
    slow = slow->next;
    fast = fast->next->next;
    if (slow == fast) return true; // Cycle detected
}
return false;""",
                javaTemplate = """// Java Fast & Slow Pointer Template
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) return true; // Cycle detected
}
return false;""",
                commonMistakes = listOf("Checking fast.next without checking fast != null first", "Infinite loop when fast pointer is not incremented properly"),
                interviewWeight = "High"
            ),
            DsaPatternModel(
                id = "pattern_tree_dfs_bfs",
                category = "Trees & Graphs",
                title = "Tree Traversal (DFS Recursion & BFS Queue)",
                coreConcept = "Process tree nodes level-by-level using Queue (BFS) or depth-first using recursion stack (DFS).",
                keyProblemsCount = 30,
                cppTemplate = """// C++ Tree Level Order BFS
queue<TreeNode*> q;
if (root) q.push(root);
while (!q.empty()) {
    int sz = q.size();
    for (int i = 0; i < sz; sz++) {
        TreeNode* curr = q.front(); q.pop();
        if (curr->left) q.push(curr->left);
        if (curr->right) q.push(curr->right);
    }
}""",
                javaTemplate = """// Java Tree Level Order BFS
Queue<TreeNode> q = new LinkedList<>();
if (root != null) q.add(root);
while (!q.isEmpty()) {
    int sz = q.size();
    for (int i = 0; i < sz; i++) {
        TreeNode curr = q.poll();
        if (curr.left != null) q.add(curr.left);
        if (curr.right != null) q.add(curr.right);
    }
}""",
                commonMistakes = listOf("Not capturing queue size `sz` before inner loop in level order BFS", "Missing null base case in recursive DFS"),
                interviewWeight = "Critical"
            ),
            DsaPatternModel(
                id = "pattern_top_k_heap",
                category = "Heaps & Hash Maps",
                title = "Top K Elements (Min-Heap / Priority Queue)",
                coreConcept = "Maintain a Min-Heap of size K to track K largest elements in O(N log K) time.",
                keyProblemsCount = 18,
                cppTemplate = """// C++ Top K Elements with Min-Heap
priority_queue<int, vector<int>, greater<int>> minHeap;
for (int num : nums) {
    minHeap.push(num);
    if (minHeap.size() > k) minHeap.pop();
}
// minHeap top is Kth largest element""",
                javaTemplate = """// Java Top K Elements with Min-Heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int num : nums) {
    minHeap.add(num);
    if (minHeap.size() > k) minHeap.poll();
}
// minHeap.peek() is Kth largest element""",
                commonMistakes = listOf("Using Max-Heap instead of Min-Heap for K largest elements (results in O(N log N) instead of O(N log K))"),
                interviewWeight = "High"
            ),
            DsaPatternModel(
                id = "pattern_graph_bfs_dfs",
                category = "Graphs",
                title = "Grid & Node Graph BFS/DFS",
                coreConcept = "Traverse graph components or 2D matrices using visited arrays to find connected components and shortest unweighted paths.",
                keyProblemsCount = 35,
                cppTemplate = """// C++ Grid DFS Template
void dfs(vector<vector<char>>& grid, int r, int c) {
    if (r < 0 || r >= grid.size() || c < 0 || c >= grid[0].size() || grid[r][c] == '0') return;
    grid[r][c] = '0'; // mark visited
    dfs(grid, r+1, c); dfs(grid, r-1, c);
    dfs(grid, r, c+1); dfs(grid, r, c-1);
}""",
                javaTemplate = """// Java Grid DFS Template
void dfs(char[][] grid, int r, int c) {
    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0') return;
    grid[r][c] = '0'; // mark visited
    dfs(grid, r+1, c); dfs(grid, r-1, c);
    dfs(grid, r, c+1); dfs(grid, r, c-1);
}""",
                commonMistakes = listOf("Forgetting visited array or modifying original grid safely leading to StackOverflowError", "Grid row/col bounds check order"),
                interviewWeight = "Critical"
            ),
            DsaPatternModel(
                id = "pattern_topological_sort",
                category = "Graphs",
                title = "Topological Sort (Kahn's Algorithm)",
                coreConcept = "Order nodes in Directed Acyclic Graph (DAG) based on in-degrees using Queue to solve dependency ordering.",
                keyProblemsCount = 15,
                cppTemplate = """// C++ Kahn's Algorithm
vector<int> inDegree(V, 0);
// compute inDegrees...
queue<int> q;
for (int i = 0; i < V; i++) if (inDegree[i] == 0) q.push(i);
vector<int> topoOrder;
while (!q.empty()) {
    int u = q.front(); q.pop();
    topoOrder.push_back(u);
    for (int v : adj[u]) {
        if (--inDegree[v] == 0) q.push(v);
    }
}
if (topoOrder.size() != V) /* Cycle detected! */;""",
                javaTemplate = """// Java Kahn's Algorithm
int[] inDegree = new int[V];
// compute inDegrees...
Queue<Integer> q = new LinkedList<>();
for (int i = 0; i < V; i++) if (inDegree[i] == 0) q.add(i);
List<Integer> topoOrder = new ArrayList<>();
while (!q.isEmpty()) {
    int u = q.poll();
    topoOrder.add(u);
    for (int v : adj.get(u)) {
        if (--inDegree[v] == 0) q.add(v);
    }
}
if (topoOrder.size() != V) /* Cycle detected! */;""",
                commonMistakes = listOf("Applying Topological Sort on graphs with undirected edges or cycles"),
                interviewWeight = "High"
            ),
            DsaPatternModel(
                id = "pattern_dp_1d_knapsack",
                category = "Dynamic Programming",
                title = "1D & 0/1 Knapsack DP Patterns",
                coreConcept = "Break problem into subproblems storing optimal decisions in 1D/2D memoization table to avoid exponential recomputation.",
                keyProblemsCount = 35,
                cppTemplate = """// C++ 0/1 Knapsack Bottom-Up DP
vector<vector<int>> dp(n + 1, vector<int>(W + 1, 0));
for (int i = 1; i <= n; i++) {
    for (int w = 1; w <= W; w++) {
        if (wt[i-1] <= w) 
            dp[i][w] = max(val[i-1] + dp[i-1][w - wt[i-1]], dp[i-1][w]);
        else 
            dp[i][w] = dp[i-1][w];
    }
}""",
                javaTemplate = """// Java 0/1 Knapsack Bottom-Up DP
int[][] dp = new int[n + 1][W + 1];
for (int i = 1; i <= n; i++) {
    for (int w = 1; w <= W; w++) {
        if (wt[i-1] <= w)
            dp[i][w] = Math.max(val[i-1] + dp[i-1][w - wt[i-1]], dp[i-1][w]);
        else
            dp[i][w] = dp[i-1][w];
    }
}""",
                commonMistakes = listOf("Confusing 0/1 Knapsack (item used once) vs Unbounded Knapsack (item used multiple times)", "Off-by-one index mapping between 0-indexed items and 1-indexed DP array"),
                interviewWeight = "Critical"
            )
        )
    }

    // --- 5. CORE CS SUBJECTS COMPARISON ---
    fun getCoreSubjects(): List<CoreSubjectModel> {
        return listOf(
            CoreSubjectModel(
                subject = "Object-Oriented Programming (OOP)",
                examFocus = "Definitions of 4 Pillars, C++/Java syntax, constructor types, function overloading theory.",
                interviewFocus = "Designing extensible class hierarchies, runtime polymorphism, abstract classes vs interfaces, SOLID principles, and live Machine Coding (Parking Lot, Tic-Tac-Toe).",
                realDevUsage = "100% daily usage when writing clean, maintainable enterprise software service components.",
                bscOverlapPercentage = "90% Overlap with College Syllabus",
                keyTopics = listOf("Abstraction vs Encapsulation", "Inheritance & Polymorphism", "Abstract Classes vs Interfaces", "SOLID Principles", "Machine Coding LLD")
            ),
            CoreSubjectModel(
                subject = "Database Management Systems (DBMS) & SQL",
                examFocus = "ER Diagram notation, Relational Algebra symbols, Normalization forms (1NF, 2NF, 3NF, BCNF) proofs.",
                interviewFocus = "Writing complex SQL queries (JOINs, Group By, Subqueries, Window Functions), B-Tree Indexing mechanics, ACID properties, Transaction isolation levels, and fixing N+1 JPA queries.",
                realDevUsage = "Essential for designing schemas, writing database queries, and tuning latency in production backends.",
                bscOverlapPercentage = "85% Overlap with College Syllabus",
                keyTopics = listOf("SQL JOINs & Group By", "ACID Properties & Transactions", "B-Tree Database Indexing", "Normalization (3NF)", "N+1 JPA Query Optimization")
            ),
            CoreSubjectModel(
                subject = "Operating Systems (OS)",
                examFocus = "FCFS / Round Robin scheduling calculations on paper, banker's algorithm matrix solving.",
                interviewFocus = "Process vs Thread, Context Switching cost, Mutex vs Semaphore, 4 Deadlock conditions & prevention, Virtual Memory, Paging, Page Faults, and Multithreaded synchronization code.",
                realDevUsage = "Helps debug concurrency bugs, CPU saturation, memory leaks, and thread pool exhaustion in servers.",
                bscOverlapPercentage = "80% Overlap with College Syllabus",
                keyTopics = listOf("Process vs Thread", "Mutex & Semaphore", "Deadlock 4 Conditions", "Virtual Memory & Paging", "Thread Pools")
            ),
            CoreSubjectModel(
                subject = "Computer Networks (CN)",
                examFocus = "OSI 7 layers definitions, IPv4 header fields, subnetting math.",
                interviewFocus = "TCP vs UDP tradeoffs, TCP 3-Way Handshake, TLS/SSL Handshake, What happens when you type google.com, DNS lookup resolution, HTTP status codes, and WebSockets.",
                realDevUsage = "Required for designing REST/gRPC APIs, network latency debugging, and microservice communication.",
                bscOverlapPercentage = "75% Overlap with College Syllabus",
                keyTopics = listOf("TCP 3-Way Handshake", "DNS Lookup Steps", "HTTP vs HTTPS (TLS)", "OSI vs TCP/IP Layers", "WebSockets & REST")
            ),
            CoreSubjectModel(
                subject = "Git & GitHub Version Control",
                examFocus = "Rarely taught in college syllabus (or brief theoretical overview).",
                interviewFocus = "Branching strategies, resolving merge conflicts, git rebase vs merge, pull requests, git bisect.",
                realDevUsage = "Mandatory every single day in every software team on Earth.",
                bscOverlapPercentage = "10% Overlap (Must Learn Independently)",
                keyTopics = listOf("Git Init, Commit, Push, Pull", "Branching & Merge Conflicts", "Git Rebase vs Merge", "GitHub Pull Request Review")
            ),
            CoreSubjectModel(
                subject = "Linux & Command Line Fundamentals",
                examFocus = "Basic Unix history and file system commands (ls, cd, mkdir).",
                interviewFocus = "Shell scripting basics, file permissions (chmod), grep, awk, sed, ps, top, systemctl, SSH keys, network debugging (curl, netstat).",
                realDevUsage = "Crucial for server administration, Docker container debugging, and cloud deployment.",
                bscOverlapPercentage = "40% Overlap (Needs Practical Extension)",
                keyTopics = listOf("Linux Directory Hierarchy", "Grep, Find, Tail, Less", "Permissions & Chmod", "SSH Keys & Remote Servers", "Process Management")
            )
        )
    }

    // --- 6. PRIMARY BACKEND STACK & 4 PORTFOLIO PROJECTS ---
    fun getPrimaryBackendStackInfo(): Map<String, Any> {
        return mapOf(
            "chosenStack" to "Java 21+ with Spring Boot 3 & PostgreSQL",
            "whyChosen" to listOf(
                "Market Volume: Java + Spring Boot powers 60%+ of backend positions in India (Amazon, Swiggy, Zerodha, Walmart, Paytm, PhonePe, and enterprise consultancies).",
                "Degree Immunity: Strong Spring Boot developers with production projects bypass B.Tech preference because recruiters urgently need candidates who can write real enterprise code.",
                "Long-Term Career Growth: Highest salary stability and smooth transition into System Architecture & Tech Lead roles.",
                "Learning Value: Teaches explicit typing, dependency injection, ORM, thread pools, and production design patterns."
            ),
            "stackComponents" to listOf(
                "Framework: Spring Boot 3 (Spring Web, Spring Security 6, Spring Data JPA)",
                "Database: PostgreSQL 16+ (Relational DB with JSONB & PGVector support)",
                "Caching: Redis (In-memory caching & distributed rate limiting)",
                "Messaging: RabbitMQ / Apache Kafka (Asynchronous event streaming)",
                "DevOps: Docker, Docker Compose, GitHub Actions, AWS EC2 / Render"
            )
        )
    }

    fun getPortfolioProjects(): List<ProjectBlueprintModel> {
        return listOf(
            ProjectBlueprintModel(
                id = "project_1_task_api",
                title = "Project 1: Enterprise RESTful Task & Workspace Management API",
                tagline = "Layered Spring Boot REST API with DTO Mapping & Custom Global Exception Handling",
                problemSolved = "Provides a clean, modular REST API for multi-tenant workspace task organization with automated validation and structured error reporting.",
                techStack = listOf("Java 21", "Spring Boot 3", "Spring Data JPA", "PostgreSQL", "Swagger/OpenAPI", "JUnit 5"),
                dbSchemaDesign = "Users table (id, email, password_hash) -> Workspaces table (id, name, owner_id) -> Tasks table (id, title, status, priority, workspace_id, assigned_user_id).",
                mainFeatures = listOf(
                    "Layered Architecture (Controller -> Service -> Repository -> DTO)",
                    "Input Validation using `@Valid` annotations and custom constraints",
                    "Global Exception Handler `@ControllerAdvice` producing standardized ErrorResponse JSON",
                    "Pagination and Sorting using `Pageable` and `Page<Task>`",
                    "OpenAPI / Swagger interactive UI documentation"
                ),
                authAndSecurity = "Basic API key / Session verification (Upgraded to JWT in Project 2).",
                apiEndpoints = listOf(
                    "POST /api/v1/workspaces - Create Workspace",
                    "GET /api/v1/workspaces/{id}/tasks?page=0&size=10&sort=dueDate,desc - Paginated Task list",
                    "POST /api/v1/tasks - Create Task with validation",
                    "PATCH /api/v1/tasks/{id}/status - Update Task status"
                ),
                testingAndDeployment = "Unit tests with JUnit 5 & Mockito for Service layer. Local PostgreSQL integration.",
                advancedFeatures = listOf("Soft deletion using `@SQLDelete`", "Database audit timestamping using `@CreatedDate` and `@LastModifiedDate`"),
                sampleInterviewQuestions = listOf(
                    "Why did you use DTOs instead of exposing JPA Entities directly in REST controllers?",
                    "How does `@ControllerAdvice` handle exceptions in Spring Boot?",
                    "What is the difference between `@Component`, `@Service`, and `@Repository`?"
                ),
                howToMakeItOriginal = "Customize business domain for a specific industry (e.g. Hospital Patient Queue API or College Lab Equipment Tracker API) instead of a generic to-do list!"
            ),
            ProjectBlueprintModel(
                id = "project_2_secure_saas",
                title = "Project 2: Secure Multi-Tenant SaaS Backend with JWT & RBAC",
                tagline = "Stateless Spring Security 6 Engine with Refresh Tokens, BCrypt & PostgreSQL Indexing",
                problemSolved = "Solves authentication and fine-grained authorization for multi-tenant enterprise software with stateless tokens and audit logging.",
                techStack = listOf("Java 21", "Spring Boot 3", "Spring Security 6", "JWT (jjwt)", "PostgreSQL", "Flyway", "Docker"),
                dbSchemaDesign = "Users (id, email, password_hash, role_enum) -> RefreshTokens (id, user_id, token, expiry_date, revoked) -> Organizations -> Subscriptions.",
                mainFeatures = listOf(
                    "Stateless SecurityFilterChain with custom `OncePerRequestFilter` for JWT extraction",
                    "BCrypt Password Hashing with salt factor 12",
                    "Role-Based Access Control (RBAC): `@PreAuthorize(\"hasRole('ADMIN')\")`",
                    "Dual-Token Architecture: Short-lived Access Token (15 mins) + Rotated Refresh Token (7 days)",
                    "Flyway versioned SQL database migrations"
                ),
                authAndSecurity = "100% Stateless JWT Auth with token revocation blacklisting and CORS configuration.",
                apiEndpoints = listOf(
                    "POST /api/v1/auth/register - Register User",
                    "POST /api/v1/auth/login - Authenticate & return Access/Refresh tokens",
                    "POST /api/v1/auth/refresh - Rotate Refresh Token",
                    "GET /api/v1/admin/users - Admin-only user management endpoint"
                ),
                testingAndDeployment = "Containerized using Docker Compose (App container + PostgreSQL container).",
                advancedFeatures = listOf("Rate Limiting on Auth endpoints to prevent brute-force attacks", "Flyway SQL migrations"),
                sampleInterviewQuestions = listOf(
                    "How does JWT token verification work without hitting the database on every request?",
                    "How do you handle Refresh Token revocation if a user's password is stolen?",
                    "What is CORS and how did you configure it in Spring Security?"
                ),
                howToMakeItOriginal = "Implement organization team invites via signed email tokens and audit log trail of user security actions."
            ),
            ProjectBlueprintModel(
                id = "project_3_distributed_rate_limiter",
                title = "Project 3: High-Throughput Distributed Rate Limiter & URL Analytics System",
                tagline = "Production Backend with Redis Caching, Token Bucket Algorithm, and System Metrics",
                problemSolved = "Protects API backends from DDoS and traffic spikes by enforcing distributed rate limits while caching high-frequency URL analytics.",
                techStack = listOf("Java 21", "Spring Boot", "Redis", "PostgreSQL", "Docker Compose", "k6 Load Testing", "GitHub Actions"),
                dbSchemaDesign = "ShortUrls (id, original_url, short_code, created_at, user_id) -> ClickAnalytics (id, short_url_id, clicked_at, ip_address, user_agent, country).",
                mainFeatures = listOf(
                    "Distributed Rate Limiter implementing Token Bucket / Sliding Window algorithm in Redis Lua script",
                    "Sub-10ms URL redirection using Redis Caching with `@Cacheable`",
                    "Asynchronous click analytics recording to prevent blocking redirect response thread",
                    "Docker Compose orchestration for App + Redis + PostgreSQL",
                    "Automated CI/CD pipeline using GitHub Actions deploying to AWS EC2 / Render"
                ),
                authAndSecurity = "API Key authentication for Tier-based Rate Limits (e.g., Free Tier: 10 req/min, Paid Tier: 100 req/min).",
                apiEndpoints = listOf(
                    "POST /api/v1/urls/shorten - Generate short URL",
                    "GET /{shortCode} - High-speed cached redirect",
                    "GET /api/v1/urls/{shortCode}/analytics - Click counts and geographic stats"
                ),
                testingAndDeployment = "k6 load test script demonstrating 1,000 requests/sec with < 20ms latency. Live deployment on Cloud VM.",
                advancedFeatures = listOf("Consistent Hashing for short code generation", "Redis Lua Script atomic execution"),
                sampleInterviewQuestions = listOf(
                    "Why did you use Redis Lua scripts for the rate limiter instead of standard Java code?",
                    "How did you prevent Cache Stampede / Thundering Herd problem on viral URL links?",
                    "How does your system handle horizontal scaling across multiple application instances?"
                ),
                howToMakeItOriginal = "Include real-time Geo-IP breakdown map in analytics and custom domain CNAME support!"
            ),
            ProjectBlueprintModel(
                id = "project_4_flagship_saas",
                title = "Project 4: Real-Time Event-Driven Platform with Kafka, WebSockets & AI Assistance",
                tagline = "Flagship Production System with Asynchronous Event Streaming, Live WebSockets, and Gemini RAG",
                problemSolved = "Delivers a production-grade scalable workspace platform featuring real-time collaborative updates, background processing, and AI-powered document intelligence.",
                techStack = listOf("Java 21", "Spring Boot 3", "Apache Kafka / RabbitMQ", "WebSockets / SSE", "Redis", "PostgreSQL (PGVector)", "Gemini AI API", "Docker", "AWS"),
                dbSchemaDesign = "Workspaces -> Documents (id, title, content, vector_embedding) -> ActivityLogs -> EventQueue.",
                mainFeatures = listOf(
                    "Event-Driven Architecture using Apache Kafka / RabbitMQ for async email notifications & analytics",
                    "Real-time bidirectional communication using Spring WebSockets & STOMP protocol",
                    "Retrieval-Augmented Generation (RAG) AI Assistant using Gemini API and PostgreSQL PGVector extension for semantic document search",
                    "Resilient Dead Letter Queue (DLQ) & retry consumer mechanisms for event processing",
                    "k6 benchmark report proving high concurrency performance"
                ),
                authAndSecurity = "JWT Auth over WebSocket handshake and REST endpoints with rate limiting.",
                apiEndpoints = listOf(
                    "WS /ws-connect - WebSocket connection endpoint for real-time room sync",
                    "POST /api/v1/ai/query - Semantic search & AI document Q&A endpoint",
                    "POST /api/v1/documents - Document upload with background vector embedding pipeline"
                ),
                testingAndDeployment = "Fully deployed on AWS EC2 / Render with GitHub Actions CI/CD and k6 performance report.",
                advancedFeatures = listOf("PGVector Semantic Search", "Kafka Consumer Group Rebalancing", "Prometheus & Grafana dashboard metrics"),
                sampleInterviewQuestions = listOf(
                    "Walk me through the lifecycle of an event from Kafka Producer to Consumer and handling failure in DLQ.",
                    "How did you implement WebSockets in Spring Boot and scale connections?",
                    "How does your RAG pipeline convert document text into vector embeddings and search PGVector?"
                ),
                howToMakeItOriginal = "Add a specific real-world industry workflow (e.g. Automated Legal Document Analyzer or Medical Lab Report Assistant) with live performance benchmarks!"
            )
        )
    }

    // --- 7. SCHEDULES DATA ---
    fun getSchedules(): List<ScheduleModel> {
        return listOf(
            ScheduleModel(
                dayType = "Tuition Day (Tuesday & Wednesday)",
                totalHours = "4.5 Hours Net Study Time",
                timeBreakdown = listOf(
                    "07:30 AM - 08:30 AM (1 Hr): Morning DSA Problem Solving (Fresh mind)",
                    "09:00 AM - 10:30 AM (1.5 Hr Train Journey): Passive Learning - Watch System Design / Core CS concepts on phone / Read documentation",
                    "11:00 AM - 03:00 PM: College / Tuition Time (Focus on college coursework)",
                    "03:00 PM - 04:30 PM (1.5 Hr Train Journey Back): Revision - Solved DSA problem dry-runs on paper or phone notes",
                    "06:00 PM - 08:00 PM (2 Hrs): Development / Spring Boot Backend Coding",
                    "09:30 PM - 10:00 PM (0.5 Hr): Daily Progress Review & GitHub Push"
                ),
                trainCommuteTip = "Use train commute time (3 hours total) for passive input: downloading video lectures, reading Spring Boot docs, or dry-running LeetCode problem algorithms on phone notes!"
            ),
            ScheduleModel(
                dayType = "Normal College Day (Mon, Thu, Fri)",
                totalHours = "4 - 5 Hours Net Study Time",
                timeBreakdown = listOf(
                    "06:30 AM - 08:30 AM (2 Hrs): Core DSA Problem Solving (2 problems target)",
                    "09:00 AM - 04:00 PM: College Classes & Academics",
                    "05:30 PM - 07:30 PM (2 Hrs): Backend Development / Project Building",
                    "08:30 PM - 09:30 PM (1 Hr): Core CS Theory (DBMS / OS / Networks) or University Exam prep",
                    "09:30 PM - 10:00 PM (0.5 Hr): Review & Git Commit"
                ),
                trainCommuteTip = "Maintain consistency over intensity. 4 focused hours every day beats an 11-hour Sunday burn-out."
            ),
            ScheduleModel(
                dayType = "Weekend / Holiday (Saturday & Sunday)",
                totalHours = "6 - 7 Hours Net Study Time",
                timeBreakdown = listOf(
                    "08:00 AM - 10:30 AM (2.5 Hrs): Timed DSA Contest / Problem Sprint (3-4 problems)",
                    "11:00 AM - 01:30 PM (2.5 Hrs): Deep Project Engineering (Spring Boot / Docker / Cloud)",
                    "03:30 PM - 05:00 PM (1.5 Hrs): System Design / Advanced Concepts / LLD Practice",
                    "06:00 PM - 07:00 PM (1 Hr): Networking, LinkedIn outreach, and Blog writing",
                    "Evening: Rest & Personal Time"
                ),
                trainCommuteTip = "Use Saturday mornings to participate in live LeetCode / Codeforces contests under timed pressure."
            )
        )
    }

    // --- 8. INTERNSHIP & JOB PLACEMENT STRATEGY ---
    fun getInternshipReadyChecklist(): List<String> {
        return listOf(
            "Solved 150+ DSA problems covering Arrays, Sliding Window, Linked Lists, Trees, Graphs, and Basic DP",
            "Project 1 & Project 2 completed, containerized with Docker, and hosted on GitHub with clean READMEs",
            "Single-page ATS Resume created in Overleaf LaTeX template with zero formatting errors",
            "GitHub profile polished with green activity graph and pinned project repositories",
            "LinkedIn profile updated with professional headline, bio, and detailed project technical bullet points",
            "Can explain REST API design, HTTP status codes, SQL JOINs, and OOP 4 pillars in plain English under 2 minutes",
            "Active profiles set up on Instahyre, Wellfound (AngelList), Hirist, and Naukri.com"
        )
    }

    fun getPlacementStrategyInfo(): Map<String, Any> {
        return mapOf(
            "bscVsBtechReality" to "Some traditional service companies and conservative product firms restrict on-campus placement drives to B.Tech/B.E. candidates. HOWEVER, modern product companies, high-growth startups, and off-campus platforms (Instahyre, Wellfound, Direct Outreach) care 90% about PROVEN SKILLS, DSA PROFICIENCY, and PRODUCTION PROJECTS.",
            "workaroundTactics" to listOf(
                "Target Startups & Scaleups (Series A to Series C): They hire based on GitHub code quality and interview performance, ignoring degree credentials.",
                "Direct Founder / EM Outreach: Cold email tech leads with a link to your live production project and a 2-sentence summary. This bypasses automated HR ATS filters.",
                "Hackathons & Open Source: Win or participate in recognized hackathons (Smart India Hackathon, MLH, Hacktoberfest) to build undeniable proof of engineering capability.",
                "Off-Campus Assessment Platforms: Score high on platforms like LeetCode Weekly Contests, Code360, and HackerRank to get direct recruiter outreach."
            )
        )
    }

    // --- 9. SALARY ANALYSIS FOR INDIAN MARKET ---
    fun getSalaryTiers(): List<SalaryTierModel> {
        return listOf(
            SalaryTierModel(
                role = "SDE Intern (3-6 Months)",
                ctcRange = "₹15,000 - ₹50,000 / month",
                baseRange = "In-hand monthly stipend",
                requirementsToAchieve = listOf("100-150 DSA problems", "1 solid Spring Boot REST API project", "Clean GitHub profile")
            ),
            SalaryTierModel(
                role = "Tier 1: Service / Entry Level SDE",
                ctcRange = "₹3.5 - ₹6 LPA",
                baseRange = "₹3.2 - ₹5.2 LPA In-hand (~₹28k - ₹42k/month)",
                requirementsToAchieve = listOf("Basic DSA (Array/Strings)", "Basic OOP & SQL", "Degree completion")
            ),
            SalaryTierModel(
                role = "Tier 2: Product Startup / High-Growth SDE-1",
                ctcRange = "₹8 - ₹14 LPA",
                baseRange = "₹7.5 - ₹12 LPA Base (~₹60k - ₹95k/month)",
                requirementsToAchieve = listOf("200+ DSA problems (Medium level fluency)", "2 strong backend projects (Spring Boot + PostgreSQL + Redis)", "Good OS & DBMS knowledge")
            ),
            SalaryTierModel(
                role = "Tier 3: Top Product / Unicorn SDE-1 (Target Range)",
                ctcRange = "₹15 - ₹25+ LPA",
                baseRange = "₹12 - ₹18 LPA Base (~₹95k - ₹1.3L/month) + Stocks/Joining Bonus",
                requirementsToAchieve = listOf("300+ DSA problems (High speed on Medium/Hard)", "4 non-trivial projects (Kafka, System Design, Cloud CI/CD)", "Clean LLD/Machine Coding & STAR behavioral delivery")
            )
        )
    }

    fun getSalaryBreakdownExplanation(): Map<String, String> {
        return mapOf(
            "CTC" to "Cost to Company - Total annual expenditure promised by employer. Often inflated with multi-year ESOPs, joining bonuses, and health insurance.",
            "Base Salary" to "The fixed annual cash pay. This is the MOST IMPORTANT number in your offer letter because monthly in-hand and future hikes are calculated on Base.",
            "Bonus" to "Variable performance bonus (usually 10-15% of base) paid yearly based on company and individual performance.",
            "ESOPs / Stocks" to "Company equity vested over 4 years (e.g. 25% per year). In unlisted startups, ESOP value is paper money until liquidity events.",
            "In-Hand Salary" to "Actual monthly cash deposited in bank account after Provident Fund (PF) and Professional/Income Tax deductions. Formula: Approx (Base Salary / 12) - PF - Tax."
        )
    }

    // --- 10. PROGRESS MILESTONES ---
    fun getMilestones(): List<MilestoneModel> {
        return listOf(
            MilestoneModel(
                monthLabel = "Month 3 Milestone",
                know = listOf("Big-O Notation", "STL/Collections", "Two Pointer & Sliding Window patterns", "Recursion mental model"),
                build = listOf("CLI Student Management System", "Undo/Redo Stack Engine"),
                solve = listOf("75+ LeetCode problems (Arrays, Strings, Linked Lists, Stacks)"),
                explain = listOf("Why Big-O matters and how Sliding Window optimizes O(N^2) to O(N)"),
                resumePoints = listOf("Fluent in C++/Java OOP and STL/Collections")
            ),
            MilestoneModel(
                monthLabel = "Month 6 Milestone",
                know = listOf("Trees, Graphs, Basic DP", "OOP 4 Pillars", "SQL JOINs and Normalization"),
                build = listOf("File Tree Indexer", "Normalized PostgreSQL E-Commerce Schema"),
                solve = listOf("150+ DSA problems (Trees, BFS/DFS, DSU, Knapsack DP)"),
                explain = listOf("Tree traversals, Graph connectivity, and SQL query execution order"),
                resumePoints = listOf("Data Structures & Algorithms proficiency (150+ problems solved)")
            ),
            MilestoneModel(
                monthLabel = "Month 9 Milestone",
                know = listOf("Spring Boot 3 REST APIs", "Spring Data JPA & Hibernate", "JWT Authentication & Spring Security 6"),
                build = listOf("Project 1 (Task API)", "Project 2 (Multi-Tenant Secure SaaS with JWT & Flyway)"),
                solve = listOf("200+ DSA problems"),
                explain = listOf("N+1 query problem fix, JWT stateless token verification lifecycle"),
                resumePoints = listOf("Project 1 & Project 2 live on GitHub with complete OpenAPI docs")
            ),
            MilestoneModel(
                monthLabel = "Month 12 Milestone",
                know = listOf("Operating Systems (Mutex/Semaphores)", "Computer Networks (TCP/IP & Handshake)", "Redis Caching", "Docker Compose"),
                build = listOf("Containerized Backend Stack with Docker Compose", "ATS Resume 1.0 & Portfolio Website"),
                solve = listOf("230+ DSA problems"),
                explain = listOf("Deadlock conditions, TCP 3-way handshake, and Cache-Aside pattern"),
                resumePoints = listOf("ATS Resume & Portfolio published. Ready for SDE Internship applications!")
            ),
            MilestoneModel(
                monthLabel = "Month 18 Milestone",
                know = listOf("System Design (Load Balancers, DB Sharding)", "Kafka / Asynchronous Event Processing", "SOLID & LLD Patterns", "AWS EC2 & GitHub Actions CI/CD"),
                build = listOf("Project 3 (Distributed Rate Limiter)", "Project 4 (Flagship Real-Time SaaS with WebSockets & RAG)"),
                solve = listOf("300+ DSA problems"),
                explain = listOf("CAP Theorem tradeoffs, Kafka event retry queues, and SOLID code architecture"),
                resumePoints = listOf("4 Non-Trivial Production Projects live on Cloud with automated CI/CD pipelines")
            ),
            MilestoneModel(
                monthLabel = "Month 24 Graduation Goal",
                know = listOf("Complete SDE Technical Stack", "AI-Enabled Engineering Workflows", "Offer Negotiation & Onboarding Playbook"),
                build = listOf("Production-ready software portfolio & live deployed systems"),
                solve = listOf("320+ High-Quality Pattern DSA problems"),
                explain = listOf("Full project defense, system architecture, and machine coding logic"),
                resumePoints = listOf("High-Earning Software Engineer Offer Secured (₹10 - 20+ LPA Target)!")
            )
        )
    }

    // --- 11. RESOURCES & THINGS NOT TO LEARN YET ---
    fun getResources(): List<ResourceModel> {
        return listOf(
            ResourceModel(
                topic = "Data Structures & Algorithms",
                primaryFree = "Striver's A2Z DSA Sheet / takeUforward (YouTube & Website)",
                backupFree = "NeetCode.io (YouTube)",
                practicePlatform = "LeetCode & Code360 (Coding Ninjas)",
                officialDocUrl = "https://takeuforward.org/"
            ),
            ResourceModel(
                topic = "Java & Spring Boot Backend",
                primaryFree = "Amigoscode / Dan Vega (YouTube Spring Boot 3 Masterclass)",
                backupFree = "Baeldung Spring Boot Tutorials",
                practicePlatform = "GitHub + Local IntelliJ IDEA",
                officialDocUrl = "https://spring.io/projects/spring-boot"
            ),
            ResourceModel(
                topic = "SQL & Databases",
                primaryFree = "SQLBolt (Interactive SQL Tutorial)",
                backupFree = "Kunal Kushwaha / Gate Smashers DBMS Playlist",
                practicePlatform = "LeetCode Database Questions",
                officialDocUrl = "https://www.postgresql.org/docs/"
            ),
            ResourceModel(
                topic = "Operating Systems & Computer Networks",
                primaryFree = "Gate Smashers OS & CN Playlists (YouTube)",
                backupFree = "Neso Academy OS & Computer Networks",
                practicePlatform = "InterviewBit Core CS Section",
                officialDocUrl = "https://developer.mozilla.org/en-US/docs/Web/HTTP"
            ),
            ResourceModel(
                topic = "System Design & Low-Level Design",
                primaryFree = "System Design Primer by Donne Martin (GitHub)",
                backupFree = "Gaurav Sen / Arpit Bhayani (YouTube)",
                practicePlatform = "ByteByteGo Newsletter / Excalidraw",
                officialDocUrl = "https://github.com/donnemartin/system-design-primer"
            ),
            ResourceModel(
                topic = "Docker & DevOps",
                primaryFree = "TechWorld with Nana (Docker & CI/CD for Beginners)",
                backupFree = "FreeCodeCamp DevOps Course",
                practicePlatform = "Play with Docker / GitHub Actions",
                officialDocUrl = "https://docs.docker.com/"
            )
        )
    }

    fun getThingsNotToLearnYet(): List<String> {
        return listOf(
            "DO NOT learn C++, Java, Python, and JavaScript all at the same time! Pick ONE primary DSA language (C++ or Java) and ONE backend stack (Java Spring Boot).",
            "DO NOT waste time on Web3, Blockchain, or Cryptocurrency development right now. Job volume for freshers is near zero in India.",
            "DO NOT attempt to build custom AI Transformer neural networks or train LLMs from scratch. Focus on SDE backend engineering and calling Gemini APIs.",
            "DO NOT dive into Kubernetes (K8s), Istio, or Terraform before you have mastered Docker and basic Cloud VMs.",
            "DO NOT memorize 500+ random LeetCode problems without pattern recognition.",
            "DO NOT watch 50-hour tutorial series without writing code yourself."
        )
    }
}
