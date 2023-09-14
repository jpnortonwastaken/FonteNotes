Welcome to FonteNotes! - the ultimate tool to streamline your planning process and increase productivity. Discover an immersive, feature-rich planning experience with an intuitive, easy-to-use interface.

![Screenshot 2023-06-21 at 1.04.40 PM.png](Screenshot%202023-06-21%20at%201.04.40%20PM.png)

## Week View

With Week View, visualize your week in an easy-to-understand, organized way. This feature represents your week as a horizontal spread just like a bullet journal. It allows you to assign names to your weeks, making your planning more personalized.

## Event and Task Creation

Stay on top of your schedules and to-do lists with our Event and Task Creation feature. Create events with a specific name, description, day of the week, start time, and duration. Create tasks with a name, description, day of the week, and mark them as complete when done.

## Persistence

Never lose your progress with our Persistence feature. With a simple click, save your data in a Week to a .bujo file using JSON encoding. When opening the program, choose a .bujo file to load and view your pre-saved contents.

## Commitment Warnings

Never overcommit again with our Commitment Warnings. Set maximum numbers of events and tasks for each day. If you exceed these limits, the planner will alert you.

## Themes

Personalize your planning with our Themes feature. Choose from a collection of pre-defined themes that change the background color or image, font color, font family. We have a special surprise in one of our themes that we will leave the user to find!

## Menu Bar & Shortcuts

Enhance your navigation experience with our Menu Bar & Shortcuts. We provide a conventional menu bar for easy access and navigation within the application.

## Mini Viewer

Get detailed views of your tasks and events with our Mini Viewer feature. Open any single event or task in a new window, displaying all its details, including its day of the week.

## Takesie-backsies

Decide to change your plans? Our Takesie-backsies feature allows you to easily delete tasks and events, keeping your Week View clutter-free.

## Progress Bar

Track your daily progress with our Progress Bar feature. Visualize the number of tasks completed vs. planned for each day. It also includes a numeric count of tasks remaining for each day.

## Links

Make your planning more resourceful with our Links feature. It parses any valid HTTP/HTTPS link in a description, making it clickable right from the program's Event/Task detail views.

## Mind Changes

Stay flexible with your plans with our Mind Changes feature. Edit any aspect of any existing Event or Task from the Week view, keeping your plans adaptable and up-to-date.

With FonteNotes, planning your week has never been this exciting and personalized. Make the most out of your week with our ultimate planning tool!!![[Screenshot 2023-06-21 at 1.04.40 PM.png]]

1. Single Responsibility Principle  
	Each class has a well-defined responsibility. The `IWeek` interface and `Week` class, for instance, are responsible for managing weekly activities, while the `Task` class focuses on handling task-specific data and operations. The `JournalController` is responsible for orchestrating the application logic, and the `JournalGuiView` is tasked with visual representation. None of these classes does more than what they're designed for, thereby upholding the SRP.
    
2. Open-Closed Principle 
	Modules can be extended without requiring modifications to existing code. For instance, the interface `IWeek` ensures that any new week-like class can be created without requiring changes to existing classes, provided it adheres to the defined contract. Similarly, the `JournalGuiView` classes can be extended to support new views without modifying the underlying Controller logic.
    
3. Liskov Substitution Principle 
	 Derived classes can be substituted for their base classes without altering the correctness of the program. The `Task` class extends the `Activity` class, but wherever an `Activity` is required, a `Task` can be used, ensuring the LSP is maintained.
    
4. Interface Segregation Principle  
	`IWeek`  is a lean interface adhering to "I". Clients like `JournalControllerImpl` that interact with instances of `IWeek` are not forced to depend on methods they do not use. This principle could be enhanced further by splitting the `IWeek` interface into multiple smaller interfaces based on the client's usage.
    
5. Dependency Inversion Principle 
	The high-level `Driver` class does not depend on low-level classes like `Week` or `Task` directly. Instead, it interacts with them via the `JournalController` and `JournalGuiView` abstractions, which are injected as dependencies. Thus, the higher-level modules depend on abstractions, not on lower-level module details.

To enhance the program's feature set, Auto #tags, a new `TagParser` class can be introduced. This class would take on the responsibility of parsing tags from `Task` or `Event` title strings.

'Sort by Name & Duration' feature, `TaskComparator` and `EventComparator` classes, would be introduced which handle the sorting of `Task` and `Event` objects, respectively. Existing classes like `Task`, `Event`, and `Week` can now accommodate new features like automatic tagging without altering current behavior.

Implementing a common `Comparator<T>` interface. Would guarantee the safe substitution of specific comparators without affecting the program's functionality.

Tagging and sorting functionalities would separated from the `IWeek` interface, delegating these responsibilities to new interfaces: `ITaggable` and `ISortable`. As a result, the `Week` class implements these new interfaces, and classes that don't need sorting or tagging avoid unnecessary dependencies.

GUI class dependencies are on the `ISortable` interface for sorting functionality and the `ITagParser` interface for parsing tags, rather than on concrete classes. This would reduces tight coupling between higher-level classes like `JournalGuiView` and the details of tag parsing and sorting.

