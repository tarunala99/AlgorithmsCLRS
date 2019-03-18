#include <stdio.h>
#include <stdlib.h>

struct Node
{
    int data;
    struct Node *next;
    struct Node *prev;
};

struct Node* freeHead = NULL;

void traverse(struct Node* head);
void insert(struct Node* head, int index, int data);
struct Node* allocate();
int delete(struct Node* head, int index);
void freeNode(struct Node* deletedNode);
void freeMemory();

int main()
{
    int dataArray[] = {1,2,3,4,5,6,7};
    size_t dataLength= sizeof(dataArray)/sizeof(dataArray[0]);//Get the length of the array
    struct Node* node =  (struct Node*) malloc(sizeof (struct Node));
    node->prev=NULL;
    node->next=NULL;
    node->data=dataArray[0];
    struct Node* head = node;
    int i=1;
    //creating the linked list
    for(i=1;i<dataLength;i++)
    {
        struct Node* tempNode =  (struct Node*) malloc(sizeof (struct Node));
        tempNode->data = dataArray[i];//(*node).data can also be used
        tempNode->next=NULL;
        tempNode->prev=node;
        node->next=tempNode;
        node=node->next;
    }
    insert(head,2,10);
    
    delete(head,3);
    
    delete(head,5);
    insert(head,2,12);
    traverse(head);
    freeMemory();
    
    
    return 0;
}

void traverse(struct Node* head)
{
    struct Node* temp = head;
    while(temp != NULL)
    {
        printf("%d\n",temp->data);
        temp = temp->next;
    }
}

void insert(struct Node* head, int index,int data)
{
    struct Node* temp = head;
    int count =0;
    while(temp != NULL && count<index)
    {
        temp = temp->next;
        count++;
    }
    struct Node* newNode = allocate();
    newNode->data=data;
    newNode->next=temp->next;
    temp->next=newNode;
}

int delete(struct Node* head, int index)
{
    struct Node* temp = head;
    int count =0;
    while(temp != NULL && count<index)
    {
        temp = temp->next;
        count++;
    }
    struct Node* deletedNode = temp->next;
    temp->next = temp->next->next;
    freeNode(deletedNode);
}

struct Node* allocate()
{
    struct Node* freeNode = NULL;
    if(freeHead!=NULL)
    {
        printf("Using a present node \n");
        freeNode = freeHead;
        freeHead = freeHead->next;
        freeHead->prev=NULL;
        freeNode->next=NULL;
    }
    else
    {
        printf("Creating new node\n");
        freeNode =  (struct Node*) malloc(sizeof (struct Node));
        freeNode->next=NULL;
        freeNode->prev=NULL;
    }
    return freeNode;
}

void freeNode(struct Node* deletedNode)
{
    if(freeHead == NULL)
    {
        printf("Free head is empty \n");
        freeHead = deletedNode;
        freeHead->next=NULL;
        freeHead->prev=NULL;
    }
    else
    {
        printf("Saving freed up node \n");
        deletedNode->next = freeHead;
        deletedNode->prev = NULL;
        freeHead = deletedNode;
    }
}

void freeMemory()
{
    while(freeHead!=NULL)
    {
        printf("Deleted value is %d \n",freeHead->data);
        free(freeHead);
        freeHead = freeHead->next;
    }
}
