<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isELIgnored="false" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
		<link rel="stylesheet" href="css/app.css">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
		<script src="js/app.js"></script>
		
		<title>My Single Page App</title>
	</head>
	
	<body data-ng-app="userOrigination" data-ng-controller="regController as vm">
		<div>
			<div layout="row">
				<div flex="100">
					<div class="menuStyle">
					Alma
					</div>
				</div>
			</div>
			
			<div layout="row">
				<div flex="33">
					<h2>Registration</h2>
					<md-content layout-padding>
						<form name="userOriginationForm">
							<md-input-container> 
								<label>
									User name:
								</label>
								<input type="text" name="username" data-ng-model="vm.user.username" data-ng-minlength="3" data-ng-maxlength="10" required>
								<div data-ng-show="userOriginationForm.username.$touched" data-ng-messages="userOriginationForm.username.$error">
									<p data-ng-show="userOriginationForm.username.$error.minlength">User name length must be at least three characters.</p>
									<p data-ng-show="userOriginationForm.username.$error.maxlength">User name length must be at longest ten characters.</p>
									<p ng-show="userOriginationForm.username.$error.required">Name is mandatory field!</p>
								</div>
							</md-input-container> <br />
							<md-input-container> 
								<label>
									Credit balance:
								</label>
									<input type="text" data-ng-model="vm.user.credit" data-ng-pattern="/^[0-9]{1,10}$/" required>
							</md-input-container> <br />
							<p>Highest qualification</p>
							<md-container> 
								<md-select data-ng-model="vm.user.school">
									<md-option value="{{qat.key}}" data-ng-repeat="qat in vm.qualificationType" selected="selected">{{qat.value}}</md-option> 
								</md-select> 
							</md-container> <br />
							<p>Pick favorite colors</p>
							<md-container>
								<label ng-repeat="color in vm.favoriteColorType">
									<md-checkbox ng-checked="color.enabled" value="color.colorCode" ng-click="color.enabled = !color.enabled" />
										{{color.colorValue}}
								</label> 
							</md-container> <br />
							<p>Gender</p>
							<md-radio-group ng-model="vm.user.gend" required>
								<md-radio-button value="MALE" aria-label="Male" ng-checked>Male</md-radio-button>
								<md-radio-button value="FEMALE" aria-label="Female">Female</md-radio-button>
							</md-radio-group>
							<md-button class="md-raised" data-ng-click="vm.register()" ng-disabled="userOriginationForm.$invalid">Register</md-button>
						</form>
					</md-content>
				</div>
			</div>
			
			<div layout="row" layout-wrap>
				<div layout="column" data-ng-repeat="user in vm.registeredUsers">
					<md-card>
						<md-card-header>
							<md-card-header-text>
							<span class="md-title">About {{user.userName}}</span>
							</md-card-header-text>
						</md-card-header>
						<md-card-title>
							<md-card-title-text>
								<span class="md-headline">Current credit balance: {{user.creditBalance}}</span>
								<span class="md-headline">School:  {{user.qualification}}</span>
							</md-card-title-text>
						</md-card-title>
						<md-card-content>
							<span class="md-headline">Favorite colors:</span>
							<p data-ng-repeat="color in user.favouriteColor">
								<span class="md-headline">{{color}}</span>
							</p>
						</md-card-content>
						<md-card-action>
							<md-button class="md-raised" ng-click="vm.deleteuser(user.userId)">Remove</md-button>
						</md-card-action>
					</md-card>
				</div>
			</div>
					
		</div>	
	</body>
</html>