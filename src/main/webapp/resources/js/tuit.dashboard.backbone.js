$(document).ready(function() {
    _.templateSettings = {
        interpolate : /\{\{(.+?)\}\}/g
    };

    window.tuits_template = _.template( $("#tuit").html() );

    window.User  = Backbone.Model.extend({});
    window.Tuit = Backbone.Model.extend({});

    window.Tuits = Backbone.Collection.extend({
       model: Tuit,
       url: '/user/tuits'
    });

    window.Timeline = new Tuits;

    window.Followers = Backbone.Collection.extend({
        model: User
    });

    window.Following = Backbone.Collection.extend({
        model: User
    });

    window.TuitView = Backbone.View.extend({

        template: window.tuits_template,
        initialize: function(){
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
            Timeline.create({  "stamp":new Date() , 'content': $('#tuit-content').val(), user:{ username: 'by you' } })
        },
        addAll: function() {
            Timeline.each(this.addOne);
        }

    });

    window.App = new TuitsApp;

});