# PromiseKeeper

## Better transparency, better financial management 

<br>

**PromiseKeeper simplifies the process of 
tracking expenses!**

### Managing budget is more important than setting a budget!


<p>For most people, staying on budget is essential for 
having a healthy life.
Not only does it stabilize our finance,
but also, it helps alleviate the stress we receive from
financial burdens. Most of us are aware of the importance 
of setting budgets; 
however, not everyone is capable of staying on budget. 
This application is for people
who want to optimize their funds by managing their finance well. </p>


<br>

### Utilize Need/Want list to make wise choices

<p> This application aims to improve the transparency 
of how much and how the user spent their money. 
The Want or Need list feature helps them decide 
whether the purchase is necessary, 
which prevents impulsive buying. 
Furthermore, the bought-want list feature helps the user know 
how they are spending money on wanted items. 
Although it is generally harder to cut budgets on needed items,
managing expenses on wanted goods can be achieved by better
decision making. Knowing their money usage is the 
first step in managing their finance well.</p>

### Aim of this project 
<p>As a student, it has always been a struggle to manage my finance. 
Staying on budget was challenging, as I was not fully aware of 
how I was spending my money. Making financial management 
more accessible is the primary aim of this application.</p>


<br>

**Let's manage our expenses with PromiseKeeper!**

<br>
<br>

## User Stories 
- As a user, I want to be able to add a need or want item
- As a user I want to be able to see my want and need list
- As a user, I want to be able to see a list of wanted
    items that were bought
- As a user I want to be able to filter my want and need list
  by priority
- As a user, I want to be able to delete items that are
    no longer wanted/needed
- As a user I want to be able to know how much
  I spent on wanted items
- As a user I want to be able to know the total amount spent
  above budgets on wanted items
- As a user, I want to be able to remove bought items from the 
want and need list.
- As a user I want to be able to edit the item description 
of items in my need or want list

<br>

- As a user I want to be able to save 
my need, want, and bought-wanted list
- As a user I want to be able to load these
lists from file

## Instructions for Grader
- You can generate the first required event related to adding 
Xs to a Y by clicking on the "Add Item" button to add items 
to need, want, or bought-wanted lists
- You can generate the second required event related to adding 
Xs to a Y by clicking on the "Remove Item" button to remove items 
from need or want lists
- You can locate my visual component by opening the app
(a splash screen will be shown)
- You can save the state of my application by
clicking on the "Save" button
- You can reload the state of my application by
  clicking on the "Load Previous Data" button


## "Phase 4: Task 2"
#### Sample:
need1 added to need list at Tue Nov 29 22:02:43 PST 2022
<br>
need2 added to need list at Tue Nov 29 22:02:43 PST 2022
<br>
want1 added to want list at Tue Nov 29 22:02:43 PST 2022
<br>
bwant1 added to bought want list at Tue Nov 29 22:02:43 PST 2022
<br>

need3 added to need list at Tue Nov 29 22:02:52 PST 2022
<br>
want2 added to want list at Tue Nov 29 22:03:04 PST 2022
<br>
need2 removed from need list at Tue Nov 29 22:03:08 PST 2022
<br>
want2 removed from want list at Tue Nov 29 22:03:11 PST 2022
<br>
bwant2 added to bought want list at Tue Nov 29 22:03:24 PST 2022

The first four lines correspond to the user loading the data. 
We are essentially 'adding' items from Json files to 
each list. 
Therefore, the previous added items 
will be logged, but the previously removed items will not 
be logged in the subsequent run
(since removed items do not get saved to the JSON files).

<br>
If an item is added or removed, the console will print out 
'item name' added to/removed from 'list name' at 'time'. 



## Phase 4: Task 3 

- The
NeedList and the WantList are a list of 'Item's, and have nearly identical
functionality. Thus, it would be better to refactor the two list 
classes into a single class, instead of having both classes implement 
the 'PlanList' interface. This can be achieved by setting a field that 
represent an id (integer), name (string) or a boolean that distinguish
each list. 
By doing so, it would be easier to update a feature (method) that is 
common in both lists.

<br>

- Also, the ToJson class and FromJson class have one method each,
and are implemented by all three list classes. Thus, we can merge 
the ToJson and FromJson interfaces into a single interface that 
has all the methods related to preserving data in JSON. 


<br>

<br> 

- Furthermore, the program can be made significanly more robust 
by using Exceptions. The classes, NeedList, WantList,
BoughtWantList, and the PromiseKeeper(ui), have a lot of methods that
have a 'REQUIRES' clause. To handle unexpected user input,
this app can be refactored using Exceptions, rather than using 
'while' loops for instance. 











