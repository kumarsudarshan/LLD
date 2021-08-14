package elevator;

class AddJobWorker implements Runnable {

    private Elevator elevator;
    private Request request;

    AddJobWorker(Elevator elevator, Request request) {
        this.elevator = elevator;
        this.request = request;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(request.getExternalRequest());
        System.out.println(request.getInternalRequest());
        elevator.addJob(request);
    }

}
