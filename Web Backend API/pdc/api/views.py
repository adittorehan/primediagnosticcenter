from django.shortcuts import render
from rest_framework import status
from rest_framework.views import APIView

from api.models import Doctor
from .serializers import *
from rest_framework.response import Response


def index(req):
    return render(req, 'api/index.html', {})


class UsersAPI(APIView):
    def get(self, request, email, password):
        try:
            user = Users.objects.filter(email=email, password=password)[0]
            if user is not None:
                return Response(data={'found': True}, status=status.HTTP_200_OK)
            else:
                return Response(data={'found': False}, status=status.HTTP_404_NOT_FOUND)
        except:
            return Response(data={'found': False}, status=status.HTTP_404_NOT_FOUND)

    # def post(self, request):
    #     serializer = UsersSerializer(data=request.data)
    #     if serializer.is_valid():
    #         serializer.save()
    #         return Response({'msg': 'Users Added'}, status=status.HTTP_201_CREATED)
    #     else:
    #         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class MedicineAPI(APIView):
    def get(self, request, pk=None):
        if pk is not None:
            medicine = Medicine.objects.get(id=pk)
            serializer = MedicineSerializer(medicine, context={"request": request})
            return Response(serializer.data)
        medicines = Medicine.objects.all()
        serializer = MedicineSerializer(medicines, context={"request": request}, many=True)
        return Response(serializer.data)

    def get(self, request, name=None):
        if name is not None:
            print(name)
            medicine = Medicine.objects.filter(name__icontains=name)
            serializer = MedicineSerializer(medicine, context={"request": request}, many=True)
            return Response(serializer.data)
        medicines = Medicine.objects.all()
        serializer = MedicineSerializer(medicines, context={"request": request}, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = MedicineSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({'msg': 'Medicine Added'}, status=status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, pk=None):
        medicine = Medicine.objects.get(id=pk)
        serializer = MedicineSerializer(medicine, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({'msg': 'Updated successfully'}, status=status.HTTP_200_OK)
        return Response({'msg': 'Failed to Update'}, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, pk=None):
        medicine = Medicine.objects.get(id=pk)
        serializer = MedicineSerializer(medicine, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({'msg': 'Updated successfully'}, status=status.HTTP_200_OK)
        return Response({'msg': 'Failed to Update'}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk=None):
        medicine = Medicine.objects.get(id=pk)
        medicine.delete()
        return Response({'msg': 'Deleted Successfully'}, status=status.HTTP_200_OK)


class DoctorAPI(APIView):
    def get(self, request, pk=None):
        if pk is not None:
            doctor = Doctor.objects.get(id=pk)
            serializer = DoctorSerializer(doctor, context={"request": request})
            return Response(serializer.data)
        doctors = Doctor.objects.all()
        serializer = DoctorSerializer(doctors, context={"request": request}, many=True)
        return Response(serializer.data)

    def get(self, request, name=None):
        if name is not None:
            print(name)
            doctor = Doctor.objects.filter(name__icontains=name)
            serializer = DoctorSerializer(doctor, context={"request": request}, many=True)
            return Response(serializer.data)
        doctors = Doctor.objects.all()
        serializer = DoctorSerializer(doctors, context={"request": request}, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = DoctorSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({'msg': 'Doctor Added'}, status=status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, pk=None):
        doctor = Doctor.objects.get(id=pk)
        serializer = DoctorSerializer(doctor, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({'msg': 'Updated successfully'}, status=status.HTTP_200_OK)
        return Response({'msg': 'Failed to Update'}, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, pk=None):
        doctor = Doctor.objects.get(id=pk)
        serializer = DoctorSerializer(doctor, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({'msg': 'Updated successfully'}, status=status.HTTP_200_OK)
        return Response({'msg': 'Failed to Update'}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk=None):
        doctor = Doctor.objects.get(id=pk)
        doctor.delete()
        return Response({'msg': 'Deleted Successfully'}, status=status.HTTP_200_OK)


class AppointmentAPI(APIView):
    def get(self, request, pk=None):
        if pk is not None:
            appointment = Appointment.objects.get(id=pk)
            serializer = AppointmentSerializer(appointment)
            return Response(serializer.data)
        appointments = Appointment.objects.all()
        serializer = AppointmentSerializer(appointments, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = AppointmentSerializer(data=request.data)
        if serializer.is_valid():
            appointment = serializer.save()
            appointment.set_appointment_status(appointment.status)
            return Response({'msg': 'Appointment Added'}, status=status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, pk=None):
        appointment = Appointment.objects.get(id=pk)
        serializer = AppointmentSerializer(appointment, data=request.data)
        if serializer.is_valid():
            appointment = serializer.save()
            appointment.set_appointment_status(appointment.status)
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.data, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, pk=None):
        appointment = Appointment.objects.get(id=pk)
        serializer = AppointmentSerializer(appointment, data=request.data, partial=True)
        if serializer.is_valid():
            appointment = serializer.save()
            appointment.set_appointment_status(appointment.status)
            return Response({'msg': 'Updated successfully'}, status=status.HTTP_200_OK)
        return Response({'msg': 'Failed to Update'}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk=None):
        appointment = Appointment.objects.get(id=pk)
        appointment.delete()
        return Response({'msg': 'Deleted Successfully'}, status=status.HTTP_200_OK)


class BlogAPI(APIView):
    def get(self, request, pk=None):
        if pk is not None:
            blog = Blog.objects.get(id=pk)
            serializer = BlogSerializer(blog, context={"request": request})
            return Response(serializer.data)
        blogs = Blog.objects.all()
        serializer = BlogSerializer(blogs, context={"request": request}, many=True)
        return Response(serializer.data)


# Storing Order
class OrderAPI(APIView):
    # TODO Make Order object with email, address, phone
    def get(self, request, pk=None):
        if pk is not None:
            order = Order.objects.get(id=pk)
            serializer = OrderSerializer(order, context={"request": request})
            return Response(serializer.data)
        orders = Order.objects.all()
        serializer = OrderSerializer(orders, context={"request": request}, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = OrderSerializer(data=request.data)
        if serializer.is_valid():
            order = serializer.save()
            order.set_order_status(order.status)
            return Response(serializer.data, status=status.HTTP_200_OK)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, pk=None):
        order = Order.objects.get(id=pk)
        serializer = OrderSerializer(order, data=request.data)
        if serializer.is_valid():
            order = serializer.save()
            order.set_order_status(order.status)
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.data, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, pk=None):
        order = Order.objects.get(id=pk)
        serializer = OrderSerializer(order, data=request.data, partial=True)
        if serializer.is_valid():
            order = serializer.save()
            order.set_order_status(order.status)
            return Response({'msg': 'Updated successfully'}, status=status.HTTP_200_OK)
        return Response({'msg': 'Failed to Update'}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk=None):
        order = Order.objects.get(id=pk)
        order.delete()
        return Response({'msg': 'Deleted Successfully'}, status=status.HTTP_200_OK)


# Storing Order of particular medicine with quantity
class OrderMedicineAPI(APIView):
    # TODO Make OrderMedicine object with order_id, medicine_id, quantity
    def get(self, request, pk=None):
        if pk is not None:
            order = Order.objects.get(id=pk)
            ordermedicines = order.ordermedicine_set.all()
            serializer = OrderMedicineSerializer(ordermedicines, context={"request": request}, many=True)
            return Response(serializer.data)
        orders = OrderMedicine.objects.all()
        serializer = OrderMedicineSerializer(orders, context={"request": request}, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = OrderMedicineSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    # def put(self, request, pk=None):
    #     order = Order.objects.get(id=pk)
    #     serializer = OrderSerializer(order, data=request.data)
    #     if serializer.is_valid():
    #         order = serializer.save()
    #         order.set_order_status(order.status)
    #         return Response(serializer.data, status=status.HTTP_200_OK)
    #     return Response(serializer.data, status=status.HTTP_400_BAD_REQUEST)
    #
    # def patch(self, request, pk=None):
    #     order = Order.objects.get(id=pk)
    #     serializer = OrderSerializer(order, data=request.data, partial=True)
    #     if serializer.is_valid():
    #         order = serializer.save()
    #         order.set_order_status(order.status)
    #         return Response({'msg': 'Updated successfully'}, status=status.HTTP_200_OK)
    #     return Response({'msg': 'Failed to Update'}, status=status.HTTP_400_BAD_REQUEST)
    #
    # def delete(self, request, pk=None):
    #     order = Order.objects.get(id=pk)
    #     order.delete()
    #     return Response({'msg': 'Deleted Successfully'}, status=status.HTTP_200_OK)
