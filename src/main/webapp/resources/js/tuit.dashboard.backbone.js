$(document).ready(function() {
    //Backbone settings
    _.templateSettings = {
        interpolate : /\{\{(.+?)\}\}/g
    };

    //templates
    window.tuits_template = _.template( $("#tuit").html() );
    window.users_template = _.template( $("#user").html() );
    window.unfollow_users_template = _.template( $("#uuser").html() );

    //models
    window.User  = Backbone.Model.extend({});
    window.Tuit = Backbone.Model.extend({});


    //UTILS
    window.reload_timeline = function(){
        $("#tuits-container").empty();
        Timeline.reset();
        Timeline.fetch();
    };

    window.simple_ajax = function(method, url, callback){
      $.ajax({
          type: method,
          url: url,
          success:function(data){ callback(data) }
      })
    };

    //collections
    window.Tuits = Backbone.Collection.extend({
       model: Tuit,
       url: '/user/tuits'
    });

    window.Timeline = new Tuits;

    window.Following = Backbone.Collection.extend({
        model: User,
        src:'/users/follow'
    });

    //views
    window.TuitView = Backbone.View.extend({
        tagName:'div',
        template: window.tuits_template,
        initialize: function(){
            this.model.bind('change', this.render, this);
            this.model.bind('destroy', this.remove, this);
        },

        render: function( ){
            $(this.el).html(this.template(this.model.toJSON()));
            return this;
        },

        remove: function() {
            $(this.el).remove();
        }
    });

    //following routes
    window.TuitRoutes = Backbone.Router.extend({
        routes:{
            "/follow/:username": "follow",
            "/unfollow/:username": "unfollow"
        },
        follow: function(username){
            simple_ajax("POST","/user/follow/"+username,function(data){
                reload_timeline();
                $('#find-user-md').modal('hide');
                $(window.unfollow_users_template(data)).appendTo('#following-list');

            });
        },
        unfollow:function(username){
            simple_ajax("DELETE","/user/follow/"+username,function(data){
                reload_timeline();
                $('#following-md').modal('hide');
            })
        }
    });

    window.Routes = new TuitRoutes();

    //main app view
    window.TuitsApp = Backbone.View.extend({
        el:$("#tuit-app"),

        events:{
            "click #new-tuit": "createTuit",
            "keyup #tuit-content": 'updateCount'
        },

        initialize: function(){
            Timeline.bind('add',   this.addOne, this);
            Timeline.bind('reset', this.addAll, this);
            Timeline.bind('all',   this.render, this);
            Timeline.fetch();
        },
        addOne: function(tuit) {
            var view = new TuitView({model: tuit });
            $(view.render().el).prependTo("#tuits-container").fadeIn();
        },
        updateCount: function(){
            $('span#charCount').html($('#tuit-content').val().length);
        },
        createTuit: function(){
            Timeline.create({  "stamp":new Date() , 'content': $('#tuit-content').val(), username: 'by You.' })
            $('#tuit-content').val("");
        },
        addAll: function() {
            Timeline.each(this.addOne);
        }
    });

    //i dont know why bb cant handle this
    $('input#username-search').keyup(function() {
        var query = $('input#username-search')[0].value;
        $('#user-search-res').empty();
        if(query){
            $.ajax({
                url: '/user/find',
                context: document.body,
                type: "GET",
                dataType: 'text',
                data: 'username='+query,
                complete: function(data){
                    $.each(jQuery.parseJSON(data.responseText), function(i, item){
                        $(window.users_template(item)).appendTo('#user-search-res');
                    });
                }
            });
        }
    });


    //fire event
    window.App = new TuitsApp;

    Backbone.history.start();
    window.Routes.navigate();


    /// checks for new tuits ... thinking :(
    window.setInterval(function(){
        simple_ajax("get","/user/tuits/last", function(data){
            _.each(data, function(tuit){
                Timeline.add(tuit);
            });
        });
    },5000);

});