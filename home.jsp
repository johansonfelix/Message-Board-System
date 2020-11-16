<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<title>MessageBoard | Home </title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">



<!--For Input Tags -->
<!-- Stylesheet -->


<!-- JavaScript -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous">
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous">
</script>
<script src="tagsinput.js"></script>


<!-- For DateRangePicker-->
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<link rel="stylesheet" href="sample.css">
<link rel="stylesheet" href="stylesheet.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">



<body>

<!-- Navbar -->
<div class="topbar">
    <div class="nav">
        <!--Come back to the small nav-->

        <a class="bar-item button hide-medium hide-large hamburger-menu" href="javascript:void(0);" onclick="openNav()"><i class="fa fa-bars"></i></a>
        <a href="#" class="logo bar-item button"><img src="logo.png" class="logo header-item header-btn" alt="logo" width="200px"></a>

        <div class="search-dropdown-hover hide-small">
            <button onclick="myFunction()" class="search-btn bar-item button hide-small"><i class="fa fa-search"></i>  Search</button>
            <div id="myDropdown" class="search-dropdown-content">
                <form action="">
                    <p class="small"><label>Search Filters</label>
                    <p>

                    <div class="input">
                        <label class="small">Search by user</label><br>
                        <input type="text" name="user" placeholder="Search by user">
                    </div>

                    <div class="input">
                        <label class="small">Search by date</label><br>
                        <input type="text" name="datefilter" placeholder="Date Range" />
                    </div>


                    <div class="input">
                        <label class="small">Search by hastags</label><br>
                        <input type="text" data-role="tagsinput" placeholder="# Hashtag first" title="Enter hashtag sign first">
                    </div>

                    <br>
                    <button class="btn button" type="submit" name="button">Apply</button>
                    <button class="btn button" type="button" name="button" onclick="clearFilters()">Clear filters</button>




                </form>
            </div>
        </div>




        <div class="dropdown-hover hide-small">
            <button class="bar-item button acct-btn">  <img src="avatar.png" class="w3-circle" style="height:23px;width:23px" alt="Avatar"> ${user.username} <i class="fas fa-caret-down"></i></button>
            <div class="acct-content">
                <a href="#" class="bar-item button">My profile</a>
                <a href="#" class="bar-item button">User Settings</a>
                <a href="/A2/logout" class="bar-item button">Log Out</a>
            </div>
        </div>



        <!--come back to notfication number badge-->

        <a href="#" class=" bar-item button nav-btn" title="Notifications"><i class="fa fa-bell"></i><span class="w3-badge w3-right small w3-green">3</span></a>


        <a href="#" class="bar-item button nav-btn" title="Messages"><i class="fa fa-envelope"></i></a>
        <a href="#" class="bar-item button nav-btn" title="Account Settings"><i class="fa fa-user"></i></a>
        <a href="#" class="bar-item button nav-btn" title="News"><i class="fas fa-globe-americas"></i></a>
    </div>
</div>


<!--come back to this-->
<!-- Navbar on small screens -->
<div id="navDemo" class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 1</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 2</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 3</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">My Profile</a>
</div>



<!-- Page Container -->
<div class="container content" style="max-width:1400px;margin-top:80px">
    <!-- The Grid -->
    <div class="row">
        <!-- Left Column -->
        <div class="col left">
            <!-- Profile -->
            <div class="profile card white">
                <div class="container">
                    <h4 class="center">My Profile</h4>
                    <p class="center"><img src="avatar.png" style="height:106px;width:106px" alt="Avatar"></p>
                    <hr>
                    <p><i class="fa fa-pencil fa-fw margin-right text-format"></i> Designer, UI</p>
                    <p><i class="fa fa-home fa-fw margin-right text-format"></i> London, UK</p>
                    <p><i class="fa fa-birthday-cake fa-fw margin-right text-format"></i> April 1, 1988</p>
                </div>
            </div>
            <br>


            <br>

            <!--remove w3 tags-->
            <!-- Interests -->
            <div class="card white hide-small">
                <div class="container">
                    <p>Popular topics</p>
                    <p>
                        <span class="tag small w3-theme-d5">US Politics</span>
                        <span class="tag small w3-theme-d4">COVID19</span>
                        <span class="tag small w3-theme-d3">NBA</span>
                        <span class="tag small w3-theme-d2">Sports</span>
                        <span class="tag small w3-theme-d1">Celebrities</span>
                        <span class="tag small w3-theme">Food</span>
                        <span class="tag small w3-theme-l1">Art</span>
                        <span class="tag small w3-theme-l2">Cars</span>
                        <span class="tag small w3-theme-l3">Computer Science</span>
                        <span class="tag small w3-theme-l4">Tech</span>
                        <span class="tag small w3-theme-l5">Nature</span>
                    </p>
                </div>
            </div>
            <br>
            <!-- End Left Column -->
        </div>

        <!-- Middle Column -->
        <div class="col middle">

            <div class="row row-padding">
                <div class="col status">
                    <div class="card white">
                        <div class="container padding-small">
                            <h6 class="opacity">Welcome ${user.username}!</h6>
                            <form action="">

                                <textarea class=" msg border row-padding " form=" " placeholder="What 's on your mind?"></textarea>
                                <div class="attachments">
                                    <label>Add to your post:</label> <input type="file" name="datafile" size="50">

                                </div>
                                <br>

                                <button class="btn button" type="submit" name="button">Post</button>

                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!--POSTS-->

            <div class="container card white"><br>
                <img src="avatar.png" alt="Avatar" class="left  margin-right" style="width:60px">
                <span class="right opacity">1 min</span>
                <h4>John Doe</h4><br>
                <hr>
                <p id="post1">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                <div class="row-padding" style="margin:0 -16px">
                    <img src="nature.jpg" style="width:100%" class="margin-bottom">
                </div>
                <button type="button" class="button abtn margin-bottom"><i class="fa fa-thumbs-up"></i>  Like</button>
                <button type="button" class="button abtn margin-bottom"><i class="fa fa-comment"></i>  Comment</button>

                <div class="right">
                    <button type="button" onclick="document.getElementById('id01').style.display='block'; document.getElementById('textarea').value = document.getElementById('post1').innerHTML;"><i class="fa fa-edit"></i></button>
                    <button type="button" onclick="document.getElementById('id02').style.display='block'"><i class="fas fa-trash-alt"></i></button>
                </div>

                <!-- Modal content-->
                <div id="id01" class="modal">
                    <div class="modal-content">
                        <div class="container">
                            <span onclick="document.getElementById('id01').style.display='none'" class="button display-topright">&times;</span>

                            <div class="container padding-small">
                                <h6 class="opacity">Update Post</h6>
                                <form action="">

                                    <textarea id="textarea" class=" msg border row-padding " form=" " placeholder="What's on your mind?"></textarea>
                                    <div class="attachments">
                                        <label>Add to your post:</label> <input id="attachment" type="file" name="datafile" size="50">

                                    </div>
                                    <br>

                                    <button class="btn button" type="submit" name="button">Post</button>

                                </form>
                            </div>

                        </div>
                    </div>
                </div>

                <!-- Modal content-->
                <div id="id02" class="modal">
                    <div class="modal-content">
                        <div class="w3-container">
                            <span onclick="document.getElementById('id02').style.display='none'" class="button display-topright">&times;</span>
                            <h6 class="opacity">Delete Post</h6>
                            <p>Are you sure you want to delete this post?</p>
                            <button class="btn button nobtn right margin-right margin-bottom" type="submit" name="button">No</button>
                            <button class="btn button yesbtn right margin-right margin-bottom" type="submit" name="button">Yes</button>

                        </div>
                    </div>
                </div>



            </div>



            <!-- End Middle Column -->
        </div>

        <!-- Right Column -->
        <div class="col ad">


            <div class="card white padding center">
                <p>AD</p>
            </div>
            <br>


        </div>
        <!-- End Right Column -->


        <!-- End Grid -->
    </div>

    <!-- End Page Container -->
</div>
<br>

<!-- Footer -->
<footer class="container">
    <h5></h5>
</footer>


<script>
    // Used to toggle the menu on smaller screens when clicking on the menu button
    function openNav() {
        var x = document.getElementById("navDemo");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }

    function myFunction() {
        document.getElementById("myDropdown").classList.toggle("show");

    }

    $(function() {

        $('input[name="datefilter" ] ').daterangepicker({
            autoUpdateInput: false,
            locale: {
                cancelLabel: 'Clear '
            }
        });

        $('input[name="datefilter" ] ').on('apply.daterangepicker ', function(ev, picker) {
            $(this).val(picker.startDate.format('MM/DD/YYYY ') + ' - ' + picker.endDate.format('MM/DD/YYYY '));
        });

        $('input[name="datefilter" ] ').on('cancel.daterangepicker ', function(ev, picker) {
            $(this).val(' ');
        });

    });



    function getFileName() {
        var x = document.getElementById('entry_value ')
        document.getElementById('fileName ').innerHTML = x.value.split('\\ ').pop()
    }

    function updatePost() {
        var modal = document.getElementById("updateModal");
        var btn = document.getElementById("updatebtn");
        var close = document.getElementById("closebtn");

        btn.onclick = f

    }
</script>

</body>

</html>

