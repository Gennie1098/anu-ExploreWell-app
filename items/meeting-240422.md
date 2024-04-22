# [Team Name]
## Team Meeting [5] - Week [8] - [22-04-2024] (20:00 - 20:50)
**Absent: Gennie Nguyen**
<br>
**Lead/scribe: Lachlan Stewart/ Qinjue Wu**

## Agreed Procedure
Stand up Procedure: 
- Kai starts to generate data according to the example datafile.
- Wikolia gives the code skeleton.
- First implement createPost, followPost method. Then test these methods and generate data at the mean time.
- Kai tests all the features at first.In week9，if there are lots of features haven't been tested, others join testing.
- After Wikolia push the code skeleton, Kai refactor her code in the right place.


## Agenda Items
| Number   |                            Item |
|:---------|--------------------------------:|
| 1        |             Everyone's progress |
| 2        |            questions about data |
| 3        | how to distribute testing tasks |

## Meeting Minutes
About data:
- Only a post can be counted as a data instance, we need 2500 posts.
- Requirement for posts: related to outdoor recreation and location.
- Kai will put 15 users in total at first. Then using the datastream to simulate users add more posts and follow posts.
- First implement createPost, followPost method. Then test these methods and generate data at the mean time.
- Firebase does not allow to save password, like in the datafile. Firebase will hide the password, and it can not be queried directly.
- Lachlan will look up map box website to help generate data.

- Fix on predetermined tags and locations.
- Need to think of about 10 tags: climbing, hiking, diving, camping...
- Locations fix on states of Australia.

Progress:
- Kai tried using AI tools to generate data.
- Eva tried using chatGPT, programming on rules to generate data.
- Wikolia designed datbase structure, gave example data, and worked out use case UML and class UML, currently working on code skeleton.
- Lachlan thought about data structures, started implementing AVL tree.

Testing tasks:
- Kai tests all the features at first.In week9，if there are lots of features haven't been tested, others join testing.


## Action Items
| Task                |   Assigned To   |  Due Date  |
|:--------------------|:---------------:|:----------:|
| basic code skeleton |    Qinjue Wu    | 22-04-2024 |
| look up map box     | Lachlan Stewart | 29-04-2024 |
| refactor code       |       Kai       | 29-04-2024 |



## Scribe Rotation
The following dictates who will scribe in this and the next meeting.
| Name |
| :---: |
| Lachlan Stewart |
