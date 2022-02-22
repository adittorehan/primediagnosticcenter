import smtplib

from django.db import models

email = 'youremail'
email_password = 'email_password'

class Users(models.Model):
    name = models.CharField(max_length=200)
    email = models.EmailField()
    password = models.CharField(max_length=20)
    admin_status = models.BooleanField(default=False)

    def get_user_id(self):
        return self.id

    def __str__(self):
        return str(self.email)


class Doctor(models.Model):
    name = models.CharField(max_length=250)
    department = models.CharField(max_length=100)
    designation = models.CharField(max_length=100)
    photo = models.ImageField(upload_to='images/', null=True)

    def get_doctor_id(self):
        return self.id

    def __str__(self):
        return str(self.get_doctor_id()) + " " + str(self.name) + " " + str(self.department) + " " + str(
            self.designation)


class Blog(models.Model):
    heading = models.CharField(max_length=200)
    body = models.TextField()
    photo = models.ImageField(upload_to='images/', null=True)

    def get_blog_id(self):
        return self.id

    def __str__(self):
        return str(self.heading)


class Appointment(models.Model):
    doctor = models.ForeignKey(Doctor, on_delete=models.CASCADE)
    user_email = models.EmailField()
    user_phone = models.CharField(max_length=20)
    date = models.DateField()
    # 0 -> Pending, 1 -> Accepted, 2 -> Rejected
    status = models.IntegerField(default=0)

    def get_appointment_id(self):
        return self.id

    def set_appointment_status(self, s):
        server = smtplib.SMTP_SSL("smtp.gmail.com", 465)
        server.login(email, email_password)
        msg = "Appointment ID: " + str(
            self.get_appointment_id()) + ". \nYour appointment request with " + self.doctor.name + "is received. We " \
                                                                                                   "will notify you. "
        if s == 1:
            msg = "Sorry, your appointment request is rejected."
        if s == 2:
            msg = "Appointment ID: " + str(
                self.get_appointment_id()) + ". \nYour appointment with " + self.doctor.name + "is scheduled at " + str(
                self.date)
        SUBJECT = "Appointment Request Status"
        message = 'Subject: {}\n\n{}'.format(SUBJECT, msg)
        try:
            server.sendmail(email, str(self.user_email), message)
        except Exception:
            pass
        server.quit()
        self.status = s
        self.save()

    def __str__(self):
        return "Appointment No. " + str(self.id)


class Medicine(models.Model):
    name = models.CharField(max_length=100)
    detail = models.CharField(max_length=200)
    price = models.FloatField(default=0.0)
    quantity = models.IntegerField(default=0)
    photo = models.ImageField(upload_to='images/', null=True)

    def __str__(self):
        return str(self.name) + str(self.detail)


class Order(models.Model):
    email = models.EmailField()
    address = models.CharField(max_length=200)
    phone = models.CharField(max_length=20)
    # 0 -> Pending, 1 -> Cancelled, 2 -> Shipped, 3 -> Completed
    status = models.IntegerField(default=0)

    def get_order_id(self):
        return self.id

    def set_order_status(self, s):
        server = smtplib.SMTP_SSL("smtp.gmail.com", 465)
        server.login(email, email_password)
        msg = "Your order is received. We will notify you."
        if s == 1:
            msg = "Sorry, we can not proceed with your order request."
        if s == 2:
            msg = "Your order " + str(self.id) + " is shipped to " + str(self.address) + "\n" + self.order_detail()
        if s == 3:
            msg = "Thank you. Your order is completed." + self.order_detail()
        SUBJECT = "Order Status"
        message = 'Subject: {}\n\n{}'.format(SUBJECT, msg)
        try:
            server.sendmail(email, self.email, message)
        except Exception:
            pass
        server.quit()
        self.status = s
        self.save()

    def order_detail(self):
        return "Order No. " + str(
            self.id) + "\nYour medicines: " + self.get_medicines() + "\nShipping Address: " + self.address

    def __str__(self):
        return "Order No. " + str(self.id)

    def get_medicines(self):
        count = 0
        return ",".join(
            ["{}. {}, {}\n".format(count + 1, ordermedicine.medicine.name, ordermedicine.medicine.detail) for
             ordermedicine in
             self.ordermedicine_set.all()])


class OrderMedicine(models.Model):
    medicine = models.ForeignKey(Medicine, on_delete=models.CASCADE)
    order = models.ForeignKey(Order, on_delete=models.CASCADE)
    quantity = models.IntegerField()
