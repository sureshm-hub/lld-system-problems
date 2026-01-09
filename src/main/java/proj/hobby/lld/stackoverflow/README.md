# StackOverflow

---

## Problem Statement

Design & Implement StackOverflow for users to post questions & answers as well as add comment. Questions can have tags.
Users can vote & search. System to assign reputation and score to users based on the quality of their contributions.

---

## Requirements

- User to post question & answer
- User can comment & vote on questions & answers
- Questions can have tags
- Search on questions, tags or user profiles
- User reputation scores

---

## Entities

- User
- Question
- Answer
- Comment
- Vote
- Tag
- VoteType
- Votable
- Commentable

---

## Design Notes

### Design Patterns

- Observer: PostObserver
- Strategy: SearchStrategy

### Do's
- type hierarchy: Content -> Post -> Question, Answer


