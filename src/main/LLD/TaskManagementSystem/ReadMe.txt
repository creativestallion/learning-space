Task management system
functional requirements
    1. A software that manages the tasks
    2. it should be able to store the tasks
    3. It should be able to handle the tasks sequentially
    4. It can create a new task
    5. It can update an existing task
    6. It can assign a particular task to a user
    7. Multiple users can do multiple tasks and multiple users can do same task.
    8. Maintains the lifecycle of the task (Crux of this problem)
    9. The user can search and filter the tasks.

Non functional requirements:
    1. It should be able to process multiple requests in parallel
    2. It should be fault tolerant (exceptional handling etc)

En( . )  ( . ) [real world objects]

1. User
    a. userId
    b. state

2. Task //
    a. TaskId
    b. state (Active, Inactive, Closed)
    c. priority
    d. List<UserId> users
    e. Title
    f. Description

3. State_ENUM
    a. Active
    b. Inactive
    c. Closed

4. Priority_Enum
   a. 0
   b. 1
   c. 2


5.TaskManagement
    a. assignTask(int taskId, int userId) // overload this method
    b. int createTask()
    c. updateStatus(int taskId) // close, active, inactive
    d. List<Task> search(String str)  // return the list of tasks having that string in title
    e. getPriority (int taskId)
    f. getStatus (int taskId)

6. UserManagement // skip
    a.

Question:
    I've one task and multiple users are updating it simulan..





