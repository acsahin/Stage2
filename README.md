# HIPO INTERNSHIP - STAGE 2

This program is made for Hipo Internship Application in 2 weeks.
First 5 days, I try to learn more about Kotlin.
I decided to use two fragments in one activity. Therefore I worked with MVVM structure.

-The main fragment is the "Members Screen".
 Which includes:
  -RecyclerView: Shows the member list.
    The items are clickable.
    DetailsFragment shows up onclickitem.
  -Button: Adds new member on click.
    MutableLiveData holds the list, updates it onbuttonclick.
  -EditText: Filters the RecyclerView with "onTextChangeListener".
    Filter does not work with original list,
    Works with its copy.
  -MemberCountText: Shows the current members count.
    
After complete the first fragment
 -I made the second one: "DetailsFragment"
   The detailsFragment shows up onclickitem of RecyclerView.
   I used Android Navigation to manage Fragments.
   With Fragment Arguments, the clicked member data is send.
  
"Service" is used for read and get data from assets (only once on create).
"Model" is the main model according to JSON file in assets.

Gson and Navigation are used additionally.

The design is made with Adobe XD.
